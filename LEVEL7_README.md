# Richal - Level 7: Save and Load

## 新增功能

现在 Richal 支持自动保存和加载任务！

### 功能说明

1. **自动保存**：每次添加、修改或删除任务时，程序会自动将所有任务保存到文件中
2. **启动加载**：程序启动时会自动从文件加载之前保存的任务
3. **数据文件位置**：`./data/duke.txt`（相对于项目根目录）

### 数据文件格式

文件使用以下格式保存任务：

```
T | 1 | read book
D | 0 | return book | June 6th
E | 0 | project meeting | Aug 6th | 2-4pm
T | 1 | join sports club
```

格式说明：
- 第一列：任务类型（T=Todo, D=Deadline, E=Event）
- 第二列：完成状态（1=已完成, 0=未完成）
- 第三列：任务描述
- 第四列及以后：附加信息（Deadline 的截止日期，Event 的开始和结束时间）

### 如何运行

从项目根目录运行：

```bash
# 编译
cd src/main/java
javac richal/*.java

# 运行（从项目根目录）
cd ../../..
java -cp src/main/java richal.Richal

# 或使用提供的脚本
./run.sh
```

### 错误处理

- 如果数据文件不存在，程序会自动创建
- 如果数据文件夹不存在，程序会自动创建
- 如果数据文件中有损坏的行，程序会跳过这些行并继续加载其他有效任务
