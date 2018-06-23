package netty.learn.myconstant;

public class MyConstant<T extends MyConstant<T>> {
	
}

class MyConstantPool<T> extends MyConstant<MyConstantPool<T>>{
	
}
