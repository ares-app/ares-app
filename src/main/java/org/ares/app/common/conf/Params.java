package org.ares.app.common.conf;

public class Params {

	public static final String SESSION_LOGIN_USER="session_login_user";
	public static final String SESSION_MESSAGE="session_message";
	
	public static final String JSP_MESSAGE="jsp_message";
	public static final String JSP_ERROR_CAUSE="jsp_error_cause";
	public static final String JSP_OPER_SUCCESS="jsp_oper_success";
	public static final String POJO_VALID_ERROR_LIST="valid_error_list";
	public static final String POJO_VALID_ERROR_FORWARD="valid_error_forward";
	public static final String SERVICE_EXECUTE_ERROR="service_error";
	public static final String METHOD_PAGE="gotoPage";
	
	public static final String APPLICATION_PARAM_KV="param_kv";
	
	public static final String OPER_SUCCESS="\u64CD\u4F5C\u6210\u529F";
	public static final String OPER_FAILED="\u64CD\u4F5C\u5931\u8D25";
	
	public static final String  NOT_FUNC_MESSAGE="\u60A8\u6CA1\u6709\u5F53\u524D\u529F\u80FD\u7684\u8BBF\u95EE\u6743\u9650!";
	public static final String  RE_LOGIN_JSP="/login.jsp";
	public static final String  ERR_USER_NAME_OR_PASS="\u7528\u6237\u65E0\u6548\u6216\u8005\u53E3\u4EE4\u9519\u8BEF!";
	public static final String  CURRENT_OPER="\u672C\u6B21\u8BF7\u6C42";
	
	public static final Boolean SUCCESS=new Boolean(true);
	public static final Boolean FAILURE=new Boolean(false);
	
	/*-----------------------------------------------------------------------------------------------*/
	public static final String PAGE="cur_request_page";
	public static final String TOTAL="total";
	public static final String REQUEST="cur_http_request";
	
	public static final String SERVICE_NAME="service_name";
	public static final String SERVICE_SUCCESS="service_success";
	public static final String SERVICE_MESSAGE="service_message";
	public static final String SERVICE_FAIL_CAUSE="service_fail_cause";
	
	/*---------------------------------------------jquery easyui front use--------------------------------------------------*/
	public static final String JSON_DATAGRID_PAGE="page";
	public static final String JSON_DATAGRID_LIST="list";
	public static final String JSON_IS_SUCCESS="success";
	public static final String JSON_MESSAGE="message";
	public static final String JSON_FAIL_CAUSE="errCause";
	
	public static final String JSON_COMBO_NAME="lab";
	public static final String JSON_COMBO_VALUE="val";
	
	public static final String USER_ID="uid";
	public static final String USER_NAME="uname";
	/*---------------------------------------------jquery easyui front use--------------------------------------------------*/
	
	/*---------------------------------------------mooc & oapi use--------------------------------------------------*/
	public static final String OAPI_SIGN="sign";//签名
	public static final String JSON_CALLBACK="jsoncallback";
	public static final String RETURN_SUCCESS="success";//返回消息
	
	public static final String RET_CODE="code";//返回码
	public static final String RET_MESSAGE="message";//返回消息
	public static final String RET_REQUEST_ID="requestid";//返回请求id供问题追踪
	
	public static final String OAPI_ACCESS_KEY="accesskey";//消费者id
	public static final String OAPI_REQUEST_TIMESTAMP="timestamp";
	public static final String OAPI_REQUEST_IP="ip";
	public static final String OAPI_SECRET_KEY="secretkey";
	
	public static final String OAPI_SERVICE_MESSAGE="oapi_service_message";
	public static final String SECRETKEY="	";//签名
	
	public static final int RET_NOT_INNER_SERVICE=440;//内部服务不存在
	public static final int RET_HTTP_PROCESS_ERROR=480;//Http Error
	
	public static final int RET_BS_PROCESS_ERROR=1000;//业务处理错误返回
	public static final int RET_PROCESS_RIGHT=0;//正确返回
	public static final int RET_USER_NOT_LOGIN=400;//未登录
	public static final int RET_NOT_AUTH=401;//未授权
	public static final int RET_NOT_SERVICE=404;//无此服务
	public static final int RET_SAME_OBJECT_NAME=405;//
	
	public static final int RET_PARAM_INVALID=-1;//参数无效
	public static final int RET_NOT_ACCESSKEY=-2;//超时
	public static final int RET_CONSUMER_SIGN_ERROR=-5;//签名sign验证无效
	public static final int RET_TIME_OUT=-6;//超时
	public static final int RET_IP_ERROR=-8;//IP验证错误
	public static final int RET_SYS_PROCESS_ERROR=-200;//系统处理错误返回
	
	public static final String MSG_SESSION_TIMEOUT="\u4F1A\u8BDD\u5DF2\u7ECF\u8FC7\u671F";//会话过期
	public static final String MSG_CONSUMER_SIGN_ERROR="\u7B7E\u540Dsign\u9A8C\u8BC1\u65E0\u6548";//签名sign验证无效
	
	public static final String UTF8_JSON_DATA_TYPE="text/json; charset=utf-8";
	public static final String GBK_JSON_DATA_TYPE="text/json; charset=gbk";
	public static final String GBK_JSON_JS_TYPE="text/javascript;charset=gbk";
	public static final String CHARSET_ENCODING="UTF-8";
	
	public static final String AJAX_REQUEST_KEY="X-Requested-With";
	public static final String AJAX_REQUEST_FLAG="xmlhttprequest";
	
	public static final String OAPI_SERVICE_URL="inner_addr";
	public static final String OAPI_SERVICE_NAME="inner_srv_name";
	public static final String OAPI_MSG_NOT_SERVICE="not found the service!";
	public static final String OAPI_MSG_NOT_OAPI_SERVICE="not found the OpenApi service!";
	
	public static final String OAPI_REQUEST_ADDR="addr";
	public static final String NOT_USER_KEY="@#$(*JH";
	public static final String JSONP_NOT_VERIFY="_";
	public static final String JSONP_USE="jsonp_use";
	public static final int MD5_VERIFY_RESULT_LEN=32;

	public static final String MSG_SIGN_ERROR="\u7B7E\u540Dsign\u9A8C\u8BC1\u65E0\u6548";//签名sign验证无效
	public static final String MSG_PARAM_ERROR="\u8BF7\u6C42\u53C2\u6570\u65E0\u6548";//请求参数无效
	public static final String MSG_IP_ERROR="IP\u9A8C\u8BC1\u9519\u8BEF";//ip验证无效
	public static final String MSG_TIMEOUT_ERROR="\u8BF7\u6C42\u8D85\u65F6";//请求超时
	public static final String MSG_NOT_ACCESSKEY="\u8BF7\u6C42\u7684accesskey\u4E0D\u5B58\u5728";//无ccesskey
	public static final String MSG_NOT_GRATED="\u65E0\u8BBF\u95EE\u6743\u9650";
	
	public static final String UTF8_JSON_JS_TYPE="text/javascript;charset=utf-8";
	
	public static final String JWT_LOGIN="jwt";
	public static final String LOGIN_JUMP_PATH="login_jump_path";
	
	/*---------------------------------------------mooc use oapi use--------------------------------------------------*/
	
}