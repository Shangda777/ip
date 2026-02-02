# Richal - Level 8: 日期和时间

## 新增功能

现在 Richal 能够理解并处理日期了！不再将日期视为简单的字符串。

### 功能说明

1. **日期解析**：程序能够解析 `yyyy-MM-dd` 格式的日期（例如 `2019-10-15`）
2. **日期格式化**：日期以更友好的格式显示：`MMM dd yyyy`（例如 `Oct 15 2019`）
3. **日期存储**：使用 `java.time.LocalDate` 存储日期，而不是字符串
4. **数据持久化**：日期以标准格式（`yyyy-MM-dd`）保存到文件中

### 使用示例

#### 添加带日期的 Deadline

```bash
deadline return book /by 2019-12-02
```

输出：
```
Got it. I've added this task:
[D][ ]return book (by: Dec 02 2019)
```

#### 添加带日期的 Event

```bash
event project meeting /from 2019-08-06 /to 2019-08-06
```

输出：
```
Got it. I've added this task:
[E][ ]project meeting (from: Aug 06 2019 to: Aug 06 2019)
```

#### 添加更多示例

```bash
deadline submit assignment /by 2019-10-15
event conference /from 2019-10-01 /to 2019-10-03
```

输出：
```
Got it. I've added this task:
[D][ ]submit assignment (by: Oct 15 2019)

Got it. I've added this task:
[E][ ]conference (from: Oct 01 2019 to: Oct 03 2019)
```

### 日期格式

#### 输入格式
- **必须使用**: `yyyy-MM-dd`（例如 `2019-10-15`）
- 年份：4 位数字
- 月份：2 位数字（01-12）
- 日期：2 位数字（01-31）

#### 显示格式
- **自动转换为**: `MMM dd yyyy`（例如 `Oct 15 2019`）
- 月份：英文缩写（Jan, Feb, Mar, ...）
- 日期：2 位数字
- 年份：4 位数字

### 数据文件格式

日期以标准格式存储在文件中：

```
D | 0 | return book | 2019-12-02
E | 0 | project meeting | 2019-08-06 | 2019-08-06
T | 0 | read book
D | 0 | submit assignment | 2019-10-15
E | 0 | conference | 2019-10-01 | 2019-10-03
```

### 错误处理

如果输入的日期格式不正确，程序会提示：

```bash
deadline test task /by 12/02/2019
```

输出：
```
OOPS!!! Invalid date format. Please use yyyy-MM-dd (e.g., 2019-10-15)
```

### 技术实现

1. **Deadline 类**
   - 使用 `LocalDate` 存储截止日期
   - 接受字符串构造函数自动解析日期
   - 提供格式化输出方法

2. **Event 类**
   - 使用 `LocalDate` 存储开始和结束日期
   - 接受字符串构造函数自动解析日期
   - 提供格式化输出方法

3. **Storage 类**
   - 自动处理日期的保存（转换为 `yyyy-MM-dd`）
   - 自动处理日期的加载（从 `yyyy-MM-dd` 解析）

### 优势

- ✅ 类型安全：使用 `LocalDate` 而不是 `String`
- ✅ 日期验证：自动验证日期是否有效
- ✅ 友好显示：以易读的格式显示日期
- ✅ 标准存储：以标准格式存储到文件
- ✅ 向后兼容：保持文件格式简单明了
