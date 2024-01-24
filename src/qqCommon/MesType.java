package qqCommon;

public interface MesType {
	//老师解读
	//1.在接口中定义了一些常量
	//2.不同的常量的值，表示不同的消息类型。
	String MESSAGE_LOGIN_SUCCEED = "1";//表示登录成功
	String MESSAGE_LOGIN_FAIL = "2";// 表示登录失败
	String MESSAGE_GET_ONLINE_USER = "3";//表示获取在线用户
	String MESSAGE_RET_ONLINE_USER = "4";//表示返回在线用户
	String MESSAGE_CLIENT_EXIT = "5";//表示客户端退出
	String MESSAGE_MES_TO_ONE = "6";//表示私发消息
	String MESSAGE_MES_TO_ALL = "7";//表示群发消息
	String MESSAGE_FILE_TO_ONE = "8";//表示私发文件
	String MESSAGE_NEWS_TO_ALL = "9";//表示服务器推送
}
