项目github地址：https://github.com/icecream154/OOAD_lab2
本项目为maven项目，生成json使用alibaba fastjson包。
Lab使用了DDD的设计，实现了bonus部分的所有需求。

项目结构:
1 inputs:
包含了需要导入的所有数据文件，包括课程信息，学生信息，专业信息，修读情况等。
2 outputs:
包含了一份样例输出文件progress_report_bonus_example.json（此文件由助教提供）。
程序生成的json报告会打印到控制台，同时生成至outputs中，
命名如下——progress_report_{studentId}_{majorName}[_{direction}].json。
3 src:项目代码
包含三个部分
① domain包
领域层，包含以下五个部分。
    entity包:包含实体类，如学生、课程、修读记录等
    factory包:管理领域对象模型的生命周期，包括创建、销毁、更新等
    service包:领域层的服务抽象(ReportService)
    value包:包含值对象，如专业(Major)、专业方向(MajorPreference)、
            培养计划要求(Requirement)、培养计划要求的匹配结果等(MatchResult)。
            这里将培养计划的每一个要求项都建模为一个类，要求需要扩展时进行横向扩展即可。
            a. 培养计划要求项均需实现Requirement接口，同时针对一些通用类型的要求实现了若干抽象类。
               如CompulsoryRequirement(必修要求抽象基类)、CreditRequirement(学分要求抽象基类)等。
               impl中具体实现类会继承抽象基类。
            b. 一个培养要求会有对应的培养要求匹配结果，MatchResult的层次结构与培养计划要求类似。

② application包
应用层。
loader包下为给种类型数据的加载器，负责从外部加载数据进入系统。
Executor.java:该类通过命令行负责接受用户的输入并进行相应操作，与用户交互。

③ Main.java
程序入口，Main.java中使用application中的loader加载初始数据，并初始化executor接受用户的输入。