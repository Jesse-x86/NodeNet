
NodeNet 是一个用于构建基于节点的工作流程的框架。\
NodeNet is a framework for building node-based workflows.

我希望人们发现这个项目很有帮助，如果他们只是试图在节点图/流程图中构建一些自动化流程并且不真正担心权限或历史记录之类的琐事。有时候Activiti对你的需求而言就是太臃肿了，而且学起来太花时间，不是吗？\
I hope people find this project helpful if they are just trying to build some automated processes in a node graph/flowchart and don't really worry about trivial things like permissions or history. Sometimes Activiti is just too bloated for your needs and takes too long to learn, right?

未来我会进一步完善JavaDoc和异常处理流程，目前的框架只是把逻辑粗糙的拼接到了一起，很多地方不多加小心都可能会引起空指针异常。我也不知道我是否遗漏了什么重要的功能，我只是尽我所能的把我能想到且有能力实现的功能塞了进去。\
In the future, I will further improve the JavaDoc and exception handling processes. The current framework only roughly splices the logic together. Null pointer exceptions may occur if you are not careful in many places. I don't know if I missed any important features, I just tried my best to include the features that I could think of and have the ability to implement.

详细的产品文档我会在完成JavaDoc之后开始编纂，不过简单来说，要在你的项目中使用基于节点的工作流需要你做这些工作：\
I will start compiling detailed product documentation after completing the JavaDoc, but in short, using node-based workflows in your project requires you to do the following:

1. 根据需求extend给定的抽象类，尤其是节点，你需要它们完成基本的工作\
Extend given abstract classes according to requirements, especially nodes, you need them to complete basic work

2. 将你扩展的类打包成jar文件放进项目目录下的一个固定位置，并将其作为加载mod的地方\
Package your extended class into a jar file and put it in a fixed location in the project directory, and use it as a place to load the mod.

3. 在你的项目中加载NodeNet，并使用一个NodeLoader实例来加载你此前写好的所有Node（你只需要call一个方法……大概？）\
Load NodeNet in your project and use a NodeLoader instance to load all the Nodes you have written before (you only need to call one method...probably?)

4. 把对应的Map传给对应的工厂方法，然后使用工厂来创建实例。\
Pass the corresponding Map to the corresponding factory method, and then use the factory to create an instance.

很不幸，我暂时没有精力把配套的前端也做出来，我会努力继续开发，不过在那之前，你恐怕得自己想办法实现前端。另外，Container也没做好，你还需要自己去实现Node之间的连接以及整个工作流的启动。\
Unfortunately, I don't have the energy to build the supporting front-end for the time being. I will work hard to continue development, but before that, you may have to find a way to implement the front-end yourself. In addition, Container is not ready yet. You still need to implement the connection between Nodes and start the entire workflow yourself.

前一个Node到下一个Node的数据迁移大约是做好了。对于各种Handler以及Settings Object，很可能你需要做的就只有继承抽象类。只有Node方面需要你做一点工作来实现每个Node（或Proxy——本质上还是Node）的业务逻辑，例如Node间IO缓冲、文件读取、网络交互……等等。\
The data migration from the previous Node to the next Node is almost complete. For various Handlers and Settings Object, most likely all you need to do is inherit the abstract class. Only the Node side requires you to do a little work to implement the business logic of each Node (or Proxy - essentially Node), such as IO buffering between Nodes, file reading, network interaction, etc.

嗯……现在看来，这个框架是不是挺没用的？\
Hmm... Now it seems that this framework is quite useless?
