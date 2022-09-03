# Final Project
## _Restaurant Reservation System_
###The project has been deployed <a href="http://restable.cyou:8888/">here</a>.
## Features
1. 采用数据结构（array, linkedlist,queue.....,如果采用某种数据结构，哪一种更好？) 或者 数据库 存储数据
2. 创建用户对象（用户名，email，电话号码，预约时间，多少人参加...)
3. 创建REST API实现对排队队列的增删改查操作
    - [X] 3.1 创建一个用户，保存到数据库里面
    - [X] 3.2 通过用户的email 对用户进行更新操作，更新预约时间
    - [X] 3.3 输入用户的email，返回一个用户的信息。
    - [X] 3.4 输入用户的email，把用户移除队列
4. 创建10个table （固定）编号 1-10 如果用户一旦预定了某个table,那么这个table就不能再被其他用户预定。3.1 创建用户的同时，思考怎么创建一个跟table的映射关系 如果所有table 都被预定之后，则不能再往队列里面添加用户，返回友好提示。 返回用户信息的同时，返回用户预定的几号桌。
5. 拓展（不要求，思考即可）
    - [ ] 5.1 在用户预定时间到前半小时，发出email通知用户
      -> spring boot email&&springboot quartz job(定时通知）
    - [ ] 5.2 怎么保证两个用户不会同时预订到一个table？--->线程安全
