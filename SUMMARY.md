# summary 

## 1.Integer
- 8种原生数据类型以及位数。
- 原生数据类型作为类的field时的默认值。
- 如果field为Object，默认值为`null`。
- Java中数组被看作对象。
- Integer最小值的补码表示为`0x80000000`,最大值为`0x7fffffff`。
- 16进制表示数时默认为`int`。
- 加减运算的时候可能溢出。最大值+1变成最小值。
- `0x1234_5678`的下划线便于阅读。

## 2.Floating

- 不同类型的数值之间的转换表

`显`代表需要显式转换，`隐`代表可以隐式转换。`X`代表自己本身。

form/to|byte|short|int|long|float|double
---|---|---|---|---|---|---
byte|X|隐|隐|隐|隐|隐
short|显|X|隐|隐|隐|隐
int|显|显|X|隐|隐|隐
long|显|显|显|X|隐|隐
float|显|显|显|显|X|隐
double|显|显|显|显|显|X

## 3.Char

- Java转义字符Escape Sequences
- `codePoint`码点和`char`的区别。 



## 4.Boolean

- 运算符优先级。`x++ x--`> 单目运算符 > 算术运算符 > 移位运算符 > 关系运算符 > 按位`&^|` > 逻辑`&&||` > `? :` > 赋值
- 按位操作没有短路，逻辑操作有短路。

## 5.Array

## 6.String

- 码点和`char`

## 7.Object

- 比较相等：基本类型用`==`, 对象用`equals()`。
- 可变长参数：被当做数组。
- 代码块：静态代码块 > 构造代码块 > 构造函数，field根据代码顺序。
- `override`重写，子类重写父类。
- `override`重载，一个类内部，函数签名`（参数类型、参数个数，不包括返回值）`不同。
- 构造函数调用链：按照继承关系链`constructor chaining`。
- 静态方法与class绑定。
- `the most derived`最近继承原则。
- `super`关键字，显示调用父类方法。

## 8.Inheritance

## 9.Reflection

class就是类的描述信息。

- 获取类名：`Employee.class`和`employee.getClass()`  
- 设置权限：`getField("XX").setAccessible()`  
- 获取运行时实例类型`Class.forName`
- 获取方法访问权限`method.getModifiers`
- 判断方法访问权限`Modifier.isXXX()`
- 获取方法`getMethods()`和`getDeclaredMethods()`  
- 完整类名：`full_qualified_name`  
- 获取实例：`newInstance()`   
- 调用方法：`invoke(instance)`  
- 获取数组成员的Type`getComponentType()`  
- 获取注解`method.getAnnotation(MyAnnotation.class) != null`
## 10.Interface

- 接口作用：规范行为。
- 接口为public，不声明的话就是package-accessible。
- abstract methods, default methods, and static methods. 接口可以包括方法签名，默认方法，静态方法和常量。只有默认方法和静态方法才能有方法体。
- 常量默认是public, static, and final。abstract method没有大括号，只有声明语句。
- default方法可以让你在接口中增加新功能。当你继承一个包含default方法的接口时：若不重写，就默认继承了该方法，可重新定义该方法为抽象方法，可重写该方法（override）。 

## 11.Lambda

- 本质上是一个匿名类。
- Lambda中的变量不可改变，必须是`final`或`effective final`。
- 定义时的环境，直接引用。
- `闭包`函数本身加运行环境。闭包可以捕获闭包内部变量、本地变量、this。

## 12.InnerClass

- 内部类调用外部类的field`OuterClass.this.year++;`
- 外部类调用内部类`this.new InnerClass().increment();`
- 函数本地类，函数内部声明，直接`new LocalClass().increment();`
- 函数内部可以声明一个匿名类，例如`Runnable`
- 静态内部类使用场景：外部类需要使用内部类，内部类无需外部类资源。如果一个类要被声明为static的，只有一种情况，就是静态内部类。

## 13.Exception

- `Unchecked Exception`运行时检查。包括`Error`和`RuntimeException`。
- `Checked Exception`编译时检查。其他`Throwable`下的都是checked异常。
- 处理方法：方法()后`throws XXException`；使用`try-catch`包围。



## 14.Generic

- 类型参数。
- 上界`? extends T`
- 下界`? super T`
- 继承多个`T1Class & T2Interface ... & TnInterface`
- 用于解决编译时错误，编译后会擦除。泛型类Class不包含泛型参数。运行时没有类型参数。
- 不能创建泛型数组。`new T[]`

## 15.Collections

- `Iterable`接口，集合继承它，唯一抽象方法`Iterator<T> iterator();`
- `Iterator`一个接口，有`boolean hasNext();`和`E next();`两个抽象方法。
- `List`的`subList()`方法可能改变原List。

## 16.Streaming

- 流的3种声明方式。

```java
//1. Individual values  
Stream stream = Stream.of("a", "b", "c"); 
//2. Arrays
String [] strArray = new String[] {"a", "b", "c"};
stream = Stream.of(strArray);
stream = Arrays.stream(strArray);
//3. Collections
List<String> list = Arrays.asList(strArray);
stream = list.stream();
```
- 常用的操作：
- `map`转换类型。
- `flatMap`:压扁。
- `Collector.of()`创建一个新的收集器。
- `groupingBy()`分组
- `reduce`聚合



