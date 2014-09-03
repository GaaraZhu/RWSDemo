RWSDemo
=======

This is a simple demo of how to handle Read/Write splitting in Java code, for detailed splitting approaches, pls refer to http://blog.csdn.net/gaarazhu/article/details/8470431


Splitting stragety:<br/>
1. Use Semisynchronous Replication for data replication between Master server and Slaver server.<br/>
2. Use AOP & EntityTransaction(JPA) for transaction management.<br/>
2. Determine to user Read/Write datasource by transaction type(Read/Write).
