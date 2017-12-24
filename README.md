#### RealSurvival
## 0.1.9 beta 

# 物品制造方法&item指令: 
首先在游戏中输入
    /rs help item
即可查看所有关于制造物品的指令

----

方法1: 使用lore来创建物品
创建一个物品很简单,假如你需要创建一个右击恢复10点水分的物品,那么把一个物品拿在手上并且输入
    /rs item lore %Thirst% %split% 10
即可创建完成一个右击恢复10点水分的物品,其中"%Thirst%"与"%split%"为关键字变量,按照默认的config中的设置的话,手上物品会有一行如下文本
    Thirst => 10
    
----

方法2: 使用NBT标签创建物品
首先,在"plugin/RealSurvival/nbtitem"文件夹下创建一个文件名为
    [name]
的文件,创建完后往其中输入以下文本:
    illness:
      list: []
    thirst: 15.0
    sleep: -10
    weight: 2.0
    energy: -1.1111111
    treatable: []
    de: -1.1111111
    md: -1.1111111
    temperature: -1.1111111
    hungery: -1.1111111
保存后再到游戏中,手上拿好物品并输入以下指令(name为刚刚创建的文件的文件名)
    /rs item setNBT name
输入完后就可以得到一个右击后获得15点水分.减少10点睡眠,在背包中重量为2的物品了,很简单的





----
第二次更新
  加入了熔炉工作台,并且可以可以支持多种摆放方式(仅限3*3*3内的方块判定且不包括4个顶点所在方块的判定)
  有指令了,/rs help workbench即可查看指令帮助

----

第一次更新
 目前很多功能没有增加进去,只有基础的数值变化和使用部分物品
