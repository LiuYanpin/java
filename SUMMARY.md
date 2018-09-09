
# summary 

[TOC]

## 1.Integer

### 1.Primitive Data Types原生数据类型(共8种)
 
[官方文档](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
 
 类型     | 位数|最小值|最大值
------ | --- |---|---
byte|8-bit|-128=-2<sup>7</sup>|127=2<sup>7</sup>-1
short|16-bit|-32768|32767
int|32-bit|-2<sup>31</sup>|2<sup>31</sup>-1
long|64-bit|-2<sup>63</sup>|2<sup>63</sup>-1
float|32-bit|
double|64-bit|
boolean|1-bit|true和false|
char|16-bit|\u0000(or 0) |\uffff (or 65,535 inclusive)

#### 2.原生类型的默认值Default Values

Data Type  |  Default Value (for fields) | 
------- | ------ | 
byte|0
short|0
int	|0
long	|0L
float	|0.0f
double	|0.0d
char	|'\u0000'
String (or any object)|  	null
boolean	|false
 
进一步推导，数组在内存中是以对象的形式表示的。对象的字段在初始化的时候会被赋初始值。
因此数组被声明长度后，里面的每个值都会被赋初始值。从内存的角度来看，声明一个带一定长度的数组之后，
在内存中给它分配了一块内存地址。因此它是有值的。
```java
boolean[] booleans = new boolean[2];  
assertFalse(booleans[0]);  
int[] ints = new int[1];
assertEquals(0, ints[0]);
char[] chars = new char[1];
assertEquals('\u0000', chars[0]);
assertEquals(0, chars[0]);
String[] strings = new String[2];
assertEquals(null, strings[0]);
```

### 3.以Integer为例解释Integer的二进制表示法

最小值为`0x80000000`，最大值为`0x7fffffff`，因为最高位是符号位。
int型为4字节。在计算机内部数值的二进制表示，有正码、反码和补码。一般采用二进制补码进行表示和运算,`MIN_VALUE = 0x80000000` 和 `MAX_VALUE = 0x7fffffff` 就是补码表示的Integer的最小值(-2^31)和最大值(2^31-1)。
一个 Integer 类型占 4 字节，一个字节占 8 位二进制码，因此一个 Integer 总共占 32 位二进制码。去除第一位的符号位，剩下 31 位来表示数值。

- 当原码为正数的时候，正数的原码、反码、补码都相同。
- 当原码为负数的时候，反码为去除符号位按位取反，补码为去除符号位按位取反再加1。

```
原码：1000 0000 0000 0000 0000 0000 0000 0001 -1 
反码：1111 1111 1111 1111 1111 1111 1111 1110 
补码：1111 1111 1111 1111 1111 1111 1111 1111

原码：1000 0000 0000 0000 0000 0000 0000 0010 -2 
反码：1111 1111 1111 1111 1111 1111 1111 1101 
补码：1111 1111 1111 1111 1111 1111 1111 1110

原码：1111 1111 1111 1111 1111 1111 1111 1111 -(2^31 - 1)= -2^31 + 1 
反码：1000 0000 0000 0000 0000 0000 0000 0000 
补码：1000 0000 0000 0000 0000 0000 0000 0001

原码：1000 0000 0000 0000 0000 0000 0000 0000 -0 约定为 -2^31 
反码：1111 1111 1111 1111 1111 1111 1111 1111 
补码：1000 0000 0000 0000 0000 0000 0000 0000
```

### 4.十六进制表示数值的时候，默认是int。

	assertEquals((short) 0x8000, Short.MIN_VALUE);
	
### 5.十六进制数表示数值的时候的特例

```
assertTrue(-0x8000_0000 < 0);
assertEquals(0x1, 1);
assertEquals(-0x1, -1);
```

### 6.关于数值的溢出问题

最大值加1或者最小值减1后会溢出。两个符号相同的数相加才可能溢出。
为了防止溢出可以使用`Math.addExact()`方法。
```
assertEquals(Integer.MAX_VALUE + 1000, Integer.MIN_VALUE + 999);
assertEquals(Integer.MAX_VALUE + 1, Integer.MIN_VALUE);
assertEquals(Integer.MIN_VALUE + 1, 0x8000_0001);
assertEquals(-1, 0xffff_ffff);
assertEquals(0x0000_0000, 0xffff_ffff + 1);
assertEquals(0x8000_0000 + 0xffff_ffff, 0x7fff_ffff);
```

### 7.其他

- `int`转`short`的时候高位直接被舍弃，没有符号位。
- 16进制表示法`0x1234_5678`中间的下划线无意义，便于阅读。
[官方文档](https://docs.oracle.com/javase/8/docs/technotes/guides/language/underscores-literals.html)  

## 2.Floating

### 不同类型的数值之间的转换表

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

### Java转义字符Escape Sequences

[官网文档](https://docs.oracle.com/javase/tutorial/java/data/characters.html)

Escape Sequence|Description
-----|----
\t	|Insert a tab in the text at this point.
\b	|Insert a backspace in the text at this point.
\n  |Insert a newline in the text at this point.
\r  |Insert a carriage return in the text at this point.
\f	|Insert a formfeed in the text at this point.
\'	|Insert a single quote character in the text at this point.
\"	|Insert a double quote character in the text at this point.
\\	|Insert a backslash character in the text at this point.

### ASCII码和Unicode

`ASCII`码是美国于上个世纪60年代制定的一套字符编码方式，
ASCII 码一共规定了128个字符的编码，这128个符号（包括32个不能打印出来的控制符号），只占用了一个字节的后面7位，最前面的一位统一规定为0。  
`Unicode`将世界上所有的符号都纳入其中。这是一种所有符号的编码。`Unicode` 只是一个符号集，它只规定了符号的二进制代码，却没有规定这个二进制代码应该如何存储。

## 4.Boolean

### Java运算符优先级Operator Precedence

[官方文档](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)

Operators|	Precedence
----|----
postfix|	`expr++ expr--`
unary|	`++expr --expr +expr -expr ~ !`
multiplicative|	`* / %`
additive|	`+ -`
shift|	`<< >> >>>`
relational|	`< > <= >= instanceof`
equality|`	== !=`
bitwise AND|`&`
bitwise exclusive OR|`^`
bitwise inclusive OR|`\|`
logical AND|	`&&`
logical OR| `\|\|`
ternary|	`? :`
assignment|	`= += -= *= /= %= &= ^= \|= <<= >>= >>>=`

### BooleanOperators

boolean a, b;  

Operation |Meaning|Note
----|-------|----
a && b|logical AND |short-circuiting
a \|\| b|logical OR|short-circuiting
a & b|boolean logical AND|not short-circuiting
a \| b|boolean logical OR|not short-circuiting
a ^ b|boolean logical exclusive OR|
!a|logical NOT|
>For &, the result value is true if both operand values are true; otherwise, the result is false.  
>For ^, the result value is true if the operand values are different; otherwise, the result is false.  
>For |, the result value is false if both operand values are false; otherwise, the result is true.

### 证明与&或\|非~的操作顺序

```
final int value1 = ~0xffff_0000 & 0xffff_0000;
//先~的话结果是：0x0000_0000, 先&的话结果是：0x0000_ffff
final int value2 = ~0xffff_0000 | 0xffff_0000;
//先~的话结果是：0xffff_ffff，先|的话结果是：0x0000_ffff
final int value3 = 0x00ff_0000 & 0xff00_ffff | 0x0000_ff00;
//先&的话结果是：0x0000_ff00, 先|的话结果是：0x0000_0000
final int value4 = 0x0000_ff00 | 0xff00_ffff & 0x00ff_0000;
//先&的话结果是：0x0000_ff00, 先|的话结果是：0x0000_0000

assertEquals(0x0000_0000, value1);
assertEquals(0xffff_ffff, value2);
assertEquals(0x0000_ff00, value3);
assertEquals(0x0000_ff00, value4);
```

## 5.Object

### Object中的equals()

[官方文档](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-)

`public boolean equals(Object obj)`  
Indicates whether some other object is "equal to" this one.
The equals method implements an equivalence relation on non-null object references:

- It is reflexive: for any non-null reference value x, x.equals(x) should return true.
- It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
- It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
- It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
- For any non-null reference value x, x.equals(null) should return false.

### Java中比较相等的方式

java中的数据类型，可分为两类： 
  
- 基本数据类型，也称原始数据类型。byte,short,char,int,long,float,double,boolean 
  他们之间的比较，应用双等号（==）,比较的是他们的值。   
- 复合数据类型(类) 
  当他们用（==）进行比较的时候，比较的是他们在内存中的存放地址，所以，除非是同一个new出来的对象，他们的比较后的结果为true，否则比较后结果为false。 JAVA当中所有的类都是继承于Object这个基类的，在Object中的基类中定义了一个equals的方法，这个方法的初始行为是比较对象的内存地 址，但在一些类库当中这个方法被覆盖掉了，如String,Integer,Date在这些类当中equals有其自身的实现，而不再是比较类在堆内存中的存放地址了。  
  对于复合数据类型之间进行equals比较，在没有覆写equals方法的情况下，他们之间的比较还是基于他们在内存中的存放位置的地址值的，因为Object的equals方法也是用双等号（==）进行比较的，所以比较后的结果跟双等号（==）的结果相同。  
- 一致性的强弱：Identity > Equality相等性 > Hashcode

### 变长参数

{...1, 2, 3} => [1, 2, 3]
可变参数==数组参数？ 在带可变参数的方法体时，读取可变参数列表时，就是以数组的方式来读取。  
[官方文档](https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html)

- In past releases, a method that took an arbitrary number of values required you to create an array and put the values into the array prior to invoking the method.
- It is still true that multiple arguments must be passed in an array, but the varargs feature automates and hides the process. Furthermore, it is upward compatible with preexisting APIs.
- 带可变参数的方法可以传入一个数组参数，但带数组参数的方法却不能传入可变参数。
- JDK不允许存在，带相同参数类型列表（Type List）的参数列表的方法在同一类中重载。
- 当可变参数方法与不带参数的方法重载时，JDK默认调用的是无参数的方法。若类中没有定义无参数的方法，则会调用可变参数的方法。  

### 代码块

- 在在类中直接定义没有任何修饰符、前缀、后缀的代码块即为构造代码块。一个类必须至少有一个构造函数，构造函数在生成对象时被调用。  
- 静态代码块，它是随着类的加载而被执行，只要类被加载了就会执行，而且只会加载一次，主要用于给类进行初始化。  
- 构造代码块，每创建一个对象时就会执行一次，且优先于构造函数，主要用于初始化不同对象共性的初始化内容和初始化实例环境。  
- 构造函数，每创建一个对象时就会执行一次。同时构造函数是给特定对象进行初始化，而构造代码是给所有对象进行初始化，作用区域不同。  
通过上面的分析，他们三者的执行顺序应该为：静态代码块 > 构造代码块 > 构造函数。  
- field的初始化顺序是由它在代码中的位置决定的。构造代码块一定是在构造函数之前执行。

### Java中的Override重写与Overload重载

#### Override:重写

[官方文档](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)
  
Override:重写是子类对父类的允许访问的方法的实现过程进行重新编写, 返回值和形参都不能改变。
- 参数列表必须完全与被重写方法的相同；
- 返回类型必须完全与被重写方法的返回类型相同；
- 访问权限不能比父类中被重写的方法的访问权限更低。例如：如果父类的一个方法被声明为public，那么在子类中重写该方法就不能声明为protected。
- 父类的成员方法只能被它的子类重写。 
- 声明为final的方法不能被重写。
- 声明为static的方法不能被重写，但是能够被再次声明。
- 子类和父类在同一个包中，那么子类可以重写父类所有方法，除了声明为private和final的方法。
- 子类和父类不在同一个包中，那么子类只能够重写父类的声明为public和protected的非final方法。
- 重写的方法能够抛出任何非强制异常，无论被重写的方法是否抛出异常。但是，重写的方法不能抛出新的强制性异常，或者比被重写方法声明的更广泛的强制性异常，反之则可以。
- 构造方法不能被重写。
- 如果不能继承一个方法，则不能重写这个方法。

### Overload:重载

重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。
每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。

- 被重载的方法必须改变参数列表(参数个数或类型不一样)；
- 被重载的方法可以改变返回类型；
- 被重载的方法可以改变访问修饰符；
- 被重载的方法可以声明新的或更广的检查异常；
- 方法能够在同一个类中或者在一个子类中被重载。
- 无法以返回值类型作为重载函数的区分标准。

## 6.String

[维基百科](https://zh.wikipedia.org/wiki/UTF-16)

### UTF-16

UTF-16是Unicode字符编码五层次模型的第三层：字符编码表（Character Encoding Form，也称为"storage format"）的一种实现方式。即把Unicode字符集的抽象码位映射为16位长的整数（即码元）的序列，用于数据存储或传递。Unicode字符的码位，需要1个或者2个16位长的码元来表示，因此这是一个变长表示。

UTF是"Unicode/UCS Transformation Format"的首字母缩写，即把Unicode字符转换为某种格式之意。UTF-16正式定义于ISO/IEC 10646-1的附录C，而RFC2781也定义了相似的做法。

Unicode的编码空间从U+0000到U+10FFFF，共有1,112,064个码位（code point）可用来映射字符. Unicode的编码空间可以划分为17个平面（plane），每个平面包含216（65,536）个码位。17个平面的码位可表示为从U+xx0000到U+xxFFFF，其中xx表示十六进制值从0016到1016，共计17个平面。第一个平面称为基本多语言平面（Basic Multilingual Plane, BMP），或称第零平面（Plane 0）。其他平面称为辅助平面（Supplementary Planes）。基本多语言平面内，从U+D800到U+DFFF之间的码位区块是永久保留不映射到Unicode字符。UTF-16就利用保留下来的0xD800-0xDFFF区块的码位来对辅助平面的字符的码位进行编码。

#### 从U+0000至U+D7FF以及从U+E000至U+FFFF的码位

第一个Unicode平面（码位从U+0000至U+FFFF）包含了最常用的字符。该平面被称为基本多语言平面，缩写为BMP（Basic Multilingual Plane, BMP）。UTF-16与UCS-2编码这个范围内的码位为16比特长的单个码元，数值等价于对应的码位. BMP中的这些码位是仅有的可以在UCS-2中表示的码位。
辅助平面（Supplementary Planes）中的码位，在UTF-16中被编码为一对16比特长的码元（即32位,4字节），称作代理对（surrogate pair），具体方法是：

#### UTF-16解码   

lead | trail |	DC00|	DC01	|   … |  	DFFF
---| ---| ---| ---| ---| ---
D800|	10000	|10001	|…	|103FF
D801	|10400|	10401|	…	|107FF
 | ⋮	|⋮	|⋮|	⋱	|⋮|
DBFF|	10FC00	|10FC01|	…	|10FFFF
码位减去0x10000,得到的值的范围为20比特长的0..0xFFFFF.

高位的10比特的值（值的范围为0..0x3FF）被加上0xD800得到第一个码元或称作高位代理（high surrogate），值的范围是0xD800..0xDBFF.由于高位代理比低位代理的值要小，所以为了避免混淆使用，Unicode标准现在称高位代理为前导代理（lead surrogates）。

低位的10比特的值（值的范围也是0..0x3FF）被加上0xDC00得到第二个码元或称作低位代理（low surrogate），现在值的范围是0xDC00..0xDFFF.由于低位代理比高位代理的值要大，所以为了避免混淆使用，Unicode标准现在称低位代理为后尾代理（trail surrogates）。
上述算法可理解为：辅助平面中的码位从U+10000到U+10FFFF，共计FFFFF个，即220=1,048,576个，需要20位来表示。如果用两个16位长的整数组成的序列来表示，第一个整数（称为前导代理）要容纳上述20位的前10位，第二个整数（称为后尾代理）容纳上述20位的后10位。还要能根据16位整数的值直接判明属于前导整数代理的值的范围（210=1024)，还是后尾整数代理的值的范围（也是210=1024）。因此，需要在基本多语言平面中保留不对应于Unicode字符的2048个码位，就足以容纳前导代理与后尾代理所需要的编码空间。这对于基本多语言平面总计65536个码位来说，仅占3.125%.

由于前导代理、后尾代理、BMP中的有效字符的码位，三者互不重叠，搜索是简单的：一个字符编码的一部分不可能与另一个字符编码的不同部分相重叠。这意味着UTF-16是自同步（self-synchronizing）:可以通过仅检查一个码元就可以判定给定字符的下一个字符的起始码元. 

#### 从U+D800到U+DFFF的码位

Unicode标准规定U+D800..U+DFFF的值不对应于任何字符。

但是在使用UCS-2的时代，U+D800..U+DFFF内的值被占用，用于某些字符的映射。但只要不构成代理对，许多UTF-16编码解码还是能把这些不匹配Unicode标准的字符映射正确的辨识、转换成合规的码元[2].按照Unicode标准，这种码元序列本来应算作编码错误。

#### 示例：

例如U+10437编码:

0x10437减去0x10000,结果为0x00437,二进制为0000 0000 0100 0011 0111。  
分区它的上10位值和下10位值（使用二进制）:0000000001 and 0000110111。  
添加0xD800到上值，以形成高位：0xD800 + 0x0001 = 0xD801。  
添加0xDC00到下值，以形成低位：0xDC00 + 0x0037 = 0xDC37。  

#### UTF-16与UCS-2的关系

UTF-16可看成是UCS-2的父集。在没有辅助平面字符（surrogate code points）前，UTF-16与UCS-2所指的是同一的意思。但当引入辅助平面字符后，就称为UTF-16了。现在若有软件声称自己支持UCS-2编码，那其实是暗指它不能支持在UTF-16中超过2字节的字集。对于小于0x10000的UCS码，UTF-16编码就等于UCS码。
UTF-16比起UTF-8，好处在于大部分字符都以固定长度的字节（2字节）存储，但UTF-16却无法兼容于ASCII编码。

## 7.Object与Interface

[原文链接](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)

### Object

- 通过`super`关键字可以调用父类的方法。如果子类的构造函数不显式调用父类的构造函数，编译器会自动调用父类的无参构造函`constructor chaining`。如果父类没有无参构造函数，编译器会报错`compile-time error`。
-  没有父类的类默认继承自Object类。子类可继承所有父类的成员，除了构造函数。
-  静态方法与class绑定，而不是object。
  
#### 类的方法和接口方法有冲突时
  
- 实例方法优先于接口方法。
- 最近继承原则。`the most derived` 
- 显示声明你要调用哪个接口的方法。`super`关键字。
- 从类继承的实例方法可以重写接口中的抽象方法。

### Interface

- 接口作用：规范行为。
- 接口为public，不声明的话就是package-accessible。
- abstract methods, default methods, and static methods. 接口可以包括方法签名，默认方法，静态方法和常量。只有默认方法和静态方法才能有方法体。
- 常量默认是public, static, and final。abstract method没有大括号，只有声明语句。
- default方法可以让你在接口中增加新功能。当你继承一个包含default方法的接口时：若不重写，就默认继承了该方法，可重新定义该方法为抽象方法，可重写该方法（override）。 

## 8.Lambda

- 本质上是一个匿名类。
- Lambda中的变量不可改变。local variables referenced from a lambda expression must be final or effectively final
- 定义时的环境，直接引用。
- 一个lambda表达式capture的变量的范围可以从它被的定义的范围扩展到它被使用的范围。
- 闭包，函数本身加运行环境。闭包可以捕获闭包变量，本地变量，this关键字，定义时this指向的东西。捕获定义时的上下文。

#### 常用的接口
  
- Consumer
- Supplier
- Function
- BiConsumer
- BiFunction

## 9.Reflection

- class就是一些类的描述信息。
- 通过类名获取class`Employee.class`
- 注意`getMethods()`和`getDeclaredMethods()`的区别
- 通过实例获取class`employee.getClass()`
- `full_qualified_name`就是一个完整的类名
- `newInstance()`方法返回的是Object
- 通过一定方式得到方法 -> `invoke(instance)`
- `getComponentType()`数组成员的Type
