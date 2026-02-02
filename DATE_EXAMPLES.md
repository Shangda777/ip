# Richal 日期使用示例

## 快速参考

### 正确的日期格式
✅ `2019-10-15`（年-月-日，使用连字符）
✅ `2020-01-01`（月份和日期必须是两位数）
✅ `2019-12-31`

### 错误的日期格式
❌ `15-10-2019`（日期在前）
❌ `2019/10/15`（使用斜杠）
❌ `10-15-2019`（月份在前）
❌ `2019-10-5`（日期不是两位数）

## 命令示例

### Deadline 命令

```bash
# 基本用法
deadline return book /by 2019-12-02

# 更多示例
deadline submit report /by 2020-03-15
deadline pay bill /by 2019-11-30
deadline finish project /by 2020-01-20
```

### Event 命令

```bash
# 基本用法
event project meeting /from 2019-08-06 /to 2019-08-06

# 多天活动
event conference /from 2019-10-01 /to 2019-10-03
event vacation /from 2020-06-15 /to 2020-06-30
event workshop /from 2019-11-20 /to 2019-11-22
```

## 完整使用流程示例

```bash
$ java -cp src/main/java richal.Richal

Hello! I'm Richal
What can I do for you?
--------------------------------

> todo buy groceries
Got it. I've added this task:
[T][ ]buy groceries
Now you have 1 tasks in the list.
--------------------------------

> deadline return book /by 2019-12-02
Got it. I've added this task:
[D][ ]return book (by: Dec 02 2019)
Now you have 2 tasks in the list.
--------------------------------

> event project meeting /from 2019-08-06 /to 2019-08-06
Got it. I've added this task:
[E][ ]project meeting (from: Aug 06 2019 to: Aug 06 2019)
Now you have 3 tasks in the list.
--------------------------------

> list
Here are the tasks in your list:
1. [T][ ]buy groceries
2. [D][ ]return book (by: Dec 02 2019)
3. [E][ ]project meeting (from: Aug 06 2019 to: Aug 06 2019)
--------------------------------

> mark 2
Nice! I've marked this task as done:
[D][X]return book (by: Dec 02 2019)
--------------------------------

> bye
Bye. Hope to see you again soon!
```

## 日期显示格式说明

| 输入格式 | 显示格式 | 说明 |
|---------|---------|------|
| `2019-01-15` | `Jan 15 2019` | 一月 |
| `2019-02-28` | `Feb 28 2019` | 二月 |
| `2019-03-10` | `Mar 10 2019` | 三月 |
| `2019-04-05` | `Apr 05 2019` | 四月 |
| `2019-05-20` | `May 20 2019` | 五月 |
| `2019-06-30` | `Jun 30 2019` | 六月 |
| `2019-07-04` | `Jul 04 2019` | 七月 |
| `2019-08-15` | `Aug 15 2019` | 八月 |
| `2019-09-01` | `Sep 01 2019` | 九月 |
| `2019-10-31` | `Oct 31 2019` | 十月 |
| `2019-11-11` | `Nov 11 2019` | 十一月 |
| `2019-12-25` | `Dec 25 2019` | 十二月 |

## 错误处理示例

```bash
> deadline test task /by 12/02/2019
OOPS!!! Invalid date format. Please use yyyy-MM-dd (e.g., 2019-10-15)
--------------------------------

> deadline test task /by 2019/12/02
OOPS!!! Invalid date format. Please use yyyy-MM-dd (e.g., 2019-10-15)
--------------------------------

> deadline test task /by 02-12-2019
OOPS!!! Invalid date format. Please use yyyy-MM-dd (e.g., 2019-10-15)
--------------------------------

# 正确的格式
> deadline test task /by 2019-12-02
Got it. I've added this task:
[D][ ]test task (by: Dec 02 2019)
```

## 提示

1. **记住格式**：`yyyy-MM-dd`（年份4位，月份2位，日期2位，用连字符连接）
2. **补零**：月份和日期必须是两位数，不足要补零（如 `01`, `05`）
3. **有效日期**：程序会验证日期是否有效（如 2月没有30日）
4. **友好显示**：输入虽然严格，但显示时会自动转换为友好格式
5. **数据持久化**：日期会正确保存和加载，不用担心格式丢失
