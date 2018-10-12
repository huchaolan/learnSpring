# learnSpring

学习Spring参考文档笔记

`setPropertyValue`和`getPropertyValue`方法用于设置和获取属性值，它由多个重载方法。比较重要是一组用于指示对象属性的约定.
表达式|描述
--------------------|--|
name                |表示属性name对应的方法setName,getName或isName
account.name        |表示嵌套属性getAcount().getName或者getAccount().setName
account[2]          |表示数组或者List属性，第三个属性
account[COMPANYNAME]|表示account是个Map,COMPANYNAME是Key
