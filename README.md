// communication
1、A 调用 B 的方法，包含传值
2、A 调用 B 的方法，包含传值和回传值
3、A 调用 B 的延时方法（模仿接口），包含传值和回传值
4、A 打开 B 的页面，包含传值（可以直接通过scheme来跳转）
5、A 获取 B 的某个对象，包含传值和返回一个具体的对象，如Fragment、presenter等（只能返回一个基本类型，即各个module都拥有的对象，获取一个对象的意义不大）
6、A 打开 B 的页面，包含传值和回传值（返回值直接使用setResult即可）

跨module的情况下，以上均可实现

问题1：如何解决RouteDispatch.getInstance().addHost(Host)方法多次调用问题
有一个module的话，需要调用一次，如果有多个，就需要调用多次，如何调用一次，或者自动将Host添加进去？
解决：利用apt和注解在编译时生成代码，在app启动后去调用生成的代码将route添加进去（暂不支持）

// schedule
简单的实现线程之间的切换