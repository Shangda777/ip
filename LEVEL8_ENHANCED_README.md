# Richal - Level 8 增强版：智能日期时间解析

## 🎯 核心功能

现在 Richal 支持：
1. ✅ **多种日期格式输入** - 自动识别并转换
2. ✅ **时间支持** - 可以指定具体的时间（几点几分）
3. ✅ **智能解析** - 程序会自动判断日期格式
4. ✅ **标准存储** - 统一以 `yyyy-MM-dd HHmm` 格式存储
5. ✅ **友好显示** - 以易读格式显示（`MMM dd yyyy, hh:mma`）

## 📝 支持的日期时间格式

### 带时间的格式

| 输入格式 | 示例 | 说明 |
|---------|------|------|
| `d/M/yyyy HHmm` | `2/12/2019 1800` | 日/月/年 时间（24小时制，无冒号）|
| `d/M/yyyy HH:mm` | `2/12/2019 18:00` | 日/月/年 时间（24小时制，有冒号）|
| `dd/MM/yyyy HHmm` | `02/12/2019 1800` | 日/月/年（补零）|
| `yyyy-MM-dd HHmm` | `2019-12-02 1800` | ISO格式 时间（无冒号）|
| `yyyy-MM-dd HH:mm` | `2019-12-02 18:00` | ISO格式 时间（有冒号）|

### 仅日期的格式（默认时间为 00:00）

| 输入格式 | 示例 | 说明 |
|---------|------|------|
| `d/M/yyyy` | `2/12/2019` | 日/月/年 |
| `dd/MM/yyyy` | `02/12/2019` | 日/月/年（补零）|
| `yyyy-MM-dd` | `2019-12-02` | ISO格式 |

## 💡 使用示例

### 示例 1：多种格式混用

```bash
> deadline return book /by 2/12/2019 1800
Got it. I've added this task:
[D][ ]return book (by: Dec 02 2019, 06:00pm)

> deadline submit report /by 2019-10-15 14:30
Got it. I've added this task:
[D][ ]submit report (by: Oct 15 2019, 02:30pm)

> event meeting /from 6/8/2019 14:00 /to 6/8/2019 16:00
Got it. I've added this task:
[E][ ]meeting (from: Aug 06 2019, 02:00pm to: Aug 06 2019, 04:00pm)
```

### 示例 2：不指定时间（默认 00:00）

```bash
> deadline assignment /by 15/10/2019
Got it. I've added this task:
[D][ ]assignment (by: Oct 15 2019, 12:00am)

> deadline project /by 2019-11-20
Got it. I've added this task:
[D][ ]project (by: Nov 20 2019, 12:00am)

> event conference /from 1/9/2019 /to 3/9/2019
Got it. I've added this task:
[E][ ]conference (from: Sep 01 2019, 12:00am to: Sep 03 2019, 12:00am)
```

### 示例 3：完整使用流程

```bash
$ java -cp src/main/java richal.Richal

Hello! I'm Richal
What can I do for you?
--------------------------------

> deadline return book /by 2/12/2019 1800
Got it. I've added this task:
[D][ ]return book (by: Dec 02 2019, 06:00pm)
--------------------------------

> event project meeting /from 6/8/2019 14:00 /to 6/8/2019 16:00
Got it. I've added this task:
[E][ ]project meeting (from: Aug 06 2019, 02:00pm to: Aug 06 2019, 04:00pm)
--------------------------------

> list
Here are the tasks in your list:
1. [D][ ]return book (by: Dec 02 2019, 06:00pm)
2. [E][ ]project meeting (from: Aug 06 2019, 02:00pm to: Aug 06 2019, 04:00pm)
--------------------------------

> mark 1
Nice! I've marked this task as done:
[D][X]return book (by: Dec 02 2019, 06:00pm)
--------------------------------

> bye
Bye. Hope to see you again soon!
```

## 📊 数据存储格式

所有日期时间都以标准格式存储在文件中：

```
D | 0 | return book | 2019-12-02 1800
D | 0 | test1 | 2019-10-15 0000
E | 0 | meeting | 2019-08-06 1400 | 2019-08-06 1600
```

格式：`yyyy-MM-dd HHmm`
- 年份：4位数字
- 月份：2位数字（01-12）
- 日期：2位数字（01-31）
- 时间：4位数字（0000-2359）

## 🎨 显示格式

格式：`MMM dd yyyy, hh:mma`

示例：
- `Dec 02 2019, 06:00pm`
- `Oct 15 2019, 02:30pm`
- `Aug 06 2019, 12:00am`

## ⚙️ 技术实现

### DateTimeParser 类

新增的工具类，负责：
1. **多格式解析**：尝试多种日期时间格式直到成功
2. **格式转换**：统一转换为 `LocalDateTime`
3. **显示格式化**：转换为友好的显示格式
4. **存储格式化**：转换为标准存储格式

### 修改的类

1. **Deadline.java**
   - 使用 `LocalDateTime` 而不是 `LocalDate`
   - 支持时间信息
   - 通过 `DateTimeParser` 解析多种格式

2. **Event.java**
   - 使用 `LocalDateTime` 而不是 `LocalDate`
   - 支持时间范围
   - 通过 `DateTimeParser` 解析多种格式

3. **Storage.java**
   - 自动处理日期时间的保存和加载
   - 无需修改，因为使用的是字符串接口

## ❌ 错误处理

如果输入的日期格式不被支持：

```bash
> deadline test /by 12-02-2019
OOPS!!! Invalid date/time format. Supported formats:
  - 2/12/2019 1800 or 2/12/2019 18:00
  - 2019-12-02 1800 or 2019-12-02 18:00
  - 2/12/2019 (defaults to 00:00)
```

## 🔍 格式识别优先级

程序按以下顺序尝试解析：

1. 先尝试带时间的格式
2. 再尝试仅日期的格式（自动添加 00:00）
3. 优先尝试 `d/M/yyyy` 格式（更灵活，1-2位数字）
4. 然后尝试 `dd/MM/yyyy` 格式（固定2位数字）
5. 最后尝试 `yyyy-MM-dd` 格式（ISO标准）

## ✨ 优势

1. **用户友好**：支持多种常见日期格式，不强制使用单一格式
2. **智能解析**：自动识别格式，无需用户指定
3. **类型安全**：使用 `LocalDateTime` 确保类型安全
4. **标准存储**：统一的存储格式，便于数据交换
5. **易读显示**：友好的显示格式，包含上午/下午标识
6. **时间支持**：可以精确到分钟，满足实际需求
7. **向后兼容**：保持简单的文件格式

## 🚀 运行方式

```bash
# 从项目根目录

# 编译
cd src/main/java
javac richal/*.java

# 运行
cd ../../..
java -cp src/main/java richal.Richal
```

## 📌 注意事项

1. **日期格式歧义**：
   - `1/2/2019` 会被解析为 2019年2月1日（dd/MM/yyyy）
   - 如果需要 2019年1月2日，请使用 `2019-01-02`

2. **时间格式**：
   - 支持 `1800`（无冒号）和 `18:00`（有冒号）两种格式
   - 使用24小时制输入
   - 显示时自动转换为12小时制（am/pm）

3. **默认时间**：
   - 如果只输入日期，时间默认为 `00:00`（午夜）
   - 显示为 `12:00am`
