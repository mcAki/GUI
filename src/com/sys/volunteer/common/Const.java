package com.sys.volunteer.common;

/**
 * 常量类
 * @author Administrator
 *
 */
public class Const {

	
	
	
	/**
	 * 用户Session的标志
	 */
	public final static String USER_SESSION_KEY="user";
	/**
	 * 用户Session的标志
	 */
	public final static String USER_MISSION_SESSION_KEY="user_mission";
	/**
	 * 用户对应的主菜单Session标记
	 */
	public final static String USER_MENUTREE_SESSION_KEY="menuTree";
	/**
	 * 用户对应的权限Session标记
	 */
	public final static String USER_PERMISSION_SESSION_KEY="permissions";
	/**
	 * 权限session标记
	 */
	public final static String PERMISSION_KEY = "permissionPageListSession";
	/**
	 * 供货商对应的主菜单Session标记
	 */
	public final static String SUPPLYMAP_SESSION_KEY="supplyMap";
	/**
	 * 记录验证码使用的Session标记
	 */
	public final static String VERIFY_IMAGE_SESSION_KEY="verifyImg";
	
	public final static String GZ_DISTRICT_APPLICATION_KEY="GZ_DISTRICT_APPLICATION_KEY";
	/**
	 * 任务类型Application Key
	 */
	public final static String MISSION_TYPE_APPLICATION_KEY="MISSION_TYPE_APPLICATION_KEY";
	/**
	 * 任务状态Application Key
	 */
	public final static String MISSION_STATE_APPLICATION_KEY="MISSION_STATE_APPLICATION_KEY";
	public final static String MISSION_POSITION_APPLICATION_KEY="MISSION_POSITION_APPLICATION_KEY";
	/**
	 * 在keyvalue表中记录smid的value对应的key
	 */
	public final static String SMS_SMID_KEY="SMS_SMID_KEY";

	public static final int BUFFER_SIZE = 16 * 1024;

	
	/**
	 * 团队建立岗位对接表--表头对照
	 */
	public final static String[] EXCEL_MISSIONPOSITION_HEADER=new String[]{"ID","总序号","业务口","岗位名称","姓名","性别","证件类型","证件号码",
	                                                                       "来源学校","联系电话","职务\n(可下拉)","所属中队编号","所属小队编号",
	                                                                       "大队代码\n(一个场馆一个大队)","中队代码\n(根据中队编号自动生成)","小队代码\n(根据小队编号自动生成)"};
	
	public final static String[] EXCEL_USERS_HEADER=new String[]{"打印PDF确认表编号", "注册编号", "中文姓",	"中文名",	"姓"	,"名",
		"出生国家/地区",	"证件所属国家/地区",	"性别", "出生日期",	"电子邮箱地址",	"曾用姓"	,"曾用名","惯用姓", "惯用名", "民族",	"身高(cm)",	
		"体重(KG)",	"鞋码",	"身体状况",	"保险受益人",	"与您的关系",	"户籍所在国家/地区",	"户籍所在省/自治区/直辖市	","户籍所在市/县",
		"户籍所在详细地址",	"证件类别",	"证件签发机购",	"证件号码",	"证件有效期截止日期","学校",	"哪个校区", "校区所在的区(市)",
		"院系",	"专业",	"班级",	"在读学历",	"学校详细地址",	"入学时间",	"预计毕业时间",	"现居地所在国家/地区",	"现居地所在省/自治区/直辖市",
		"现居地所在市/县",	"现居地所在区",	"现居地所在详细地址",	"实际工作单位/就读学校名称",	"工作单位/就读学校详细地址",	"目前的职业",
		"申请人移动电话",	"申请人固定电话",	"其他联系人姓名",	"其他联系人移动电话",	"语言",	"擅长外语", "来源单位",	"亚组委招募部门",
		"普通/专业志愿者","PDF确认表是否确认", "状态", "职位",	"场馆",	"业务口",	"是否上传照片"};

	/**
	 * 性别--男
	 */
	public final static int USER_GENDER_MALE=1;
	/**
	 * 性别--女
	 */
	public final static int USER_GENDER_FEMALE=2;
	/**
	 * 性别--未知
	 */
	public final static int USER_GENDER_UNKNOWN=0;
	
	/**
	 * Log4J里面用的标记头
	 * @author Administrator
	 *
	 */
	public static class LogConst{
		public final static String USERLOGIN = "[USERLOGIN]: ";
		public final static String ATTENDANCE_POS = "[ATTENDANCE_POS]: ";
	}

	/**
	 * 任务状态
	 * @author Administrator
	 *
	 */
	public static class MissionStateType{
		/**
		 * 立项
		 */
		public final static int CREATION=1;
		/**
		 * 待审批
		 */
		public final static int WAIT_VERIFY=20;
		/**
		 * 审批通过
		 */
		public final static int VERIFY_PASSED=25;
		/**
		 * 审批不通过
		 */
		public final static int VERIFY_NOTPASS=26;
		/**
		 * 开始招募
		 */
		public final static int RECUEIT_START=30;
		/**
		 * 结束招募
		 */
		public final static int RECUEIT_END=35;
		/**
		 * 队伍分配(已停用)
		 */
		public final static int SET_TEAM=40;
		/**
		 * 任务执行
		 */
		public final static int EXECUTING=50;
		/**
		 * 任务完成
		 */
		public final static int EXECUTED=100;
		/**
		 * 项目已经结束
		 */
		public final static int FINISHED=1000;
		/**
		 * 项目争议
		 */
		public final static int CONTROVERSY=1002;

		/**
		 * 项目已禁止
		 */
		public final static int STOPPED=1001;
		
		/**
		 * 项目关闭
		 */
		public final static int CLOSED=1003;		
	}
	
	/**
	 * 读写权限
	 * @author Administrator
	 *
	 */
	public class PrivilegeAccessConst{
		/**
		 * 无效
		 */
		public final static int NONEED=-1;
		/**
		 * 功能允许使用
		 */
		public final static int ALLOW=1;
		/**
		 * 基本功能权限（无需区分的权限）
		 */
		public final static int NORMAL=0;
		/**
		 * 查
		 */
		public final static int QUERY=10;
		/**
		 * 改
		 */
		public final static int MODIFY=20;
		/**
		 * 增
		 */
		public final static int ADD=30;
		/**
		 * 删
		 */
		public final static int DEL=40;
		/**
		 * 全部
		 */
		public final static int ALL=255;
	}
	
	/**
	 * 订单状态
	 * @author admin
	 *
	 */
	public class MainOrderState{
		/**
		 * 申请处理
		 */
		public final static int APPLY_PROCESS=0;
		/**
		 * 处理成功
		 */
		public final static int PROCESS_SUCCESS=1;
		/**
		 * 处理失败
		 */
		public final static int PROCESS_FAILED=2;
		/**
		 * 处理中
		 */
		public final static int PROCESSING=3;
		/**
		 * 已处理
		 */
		public final static int PROCESSED=4;
		/**
		 * 线程执行中
		 */
		public final static int THREAD_EXECUTING=5;
		/**
		 * 用户申请取消
		 */
		public final static int USER_CANCEL_APPLY=253;
		/**
		 * 用户取消处理中
		 */
		public final static int USER_CANCEL_PROCESSING=254;
		/**
		 * 用户取消
		 */
		public final static int USER_CANCEL=255;
	}
	
	/**
	 * 订单冲正状态
	 * @author Administrator
	 *
	 */
	public class OrderReversalState{
		/**
		 * 未冲正
		 */
		public final static int NON_REVERSAL=-1;
		/**
		 * 申请处理
		 */
		public final static int APPLY_REVERSAL=-2;
		/**
		 * 处理中
		 */
		public final static int PROCESSING=0;
		/**
		 * 处理成功
		 */
		public final static int PROCESS_SUCCESS=1;
		/**
		 * 处理失败
		 */
		public final static int PROCESS_FAILED=2;
		/**
		 * 已冲正
		 */
		public final static int PROCESSED=3;
		/**
		 * 线程执行中
		 */
		public final static int THREAD_EXECUTING=5;
	}
	
	public class BatchOrderState{
		/**
		 * 待确认
		 */
		public final static int WAIT_COMMIT=0;
		/**
		 * 未开始
		 */
		public final static int NONE_PROCESS=1;
		/**
		 * 处理中
		 */
		public final static int PROCESSING=2;
		/**
		 * 暂停
		 */
		public final static int PAUSE=3;
		/**
		 * 停止
		 */
		public final static int STOP=4;
		/**
		 * 已完成
		 */
		public final static int PROCESSED=255;
	}
	
	/**
	 * 操作记录类型
	 * @author admin
	 *
	 */
	public class OperationLogType{
		/**
		 * 未知
		 */
		public final static int UNKNOWN=0;
		/**
		 * 空中充值
		 */
		public final static int MOBILE_RECHARGE=1;
		/**
		 * 卡密
		 */
		public final static int CARDLIB_BUY=2;
		/**
		 * 冲正
		 */
		public final static int REVERSAL=3;
		/**
		 * 强制订单成功
		 */
		public final static int DEAL_SUCCESS=4;
		/**
		 * 用户登陆
		 */
		public final static int LOGIN = 5;
		/**
		 * 修改密码
		 */
		public final static int MODIFY_PWD = 6;
		/**
		 * 重置密码
		 */
		public final static int RESET_PWD = 7;
		/**
		 * 修改电话号码
		 */
		public final static int MODIFY_MOBILE = 8;
		/**
		 * 用户取消
		 */
		public final static int USER_CANCEL=255;
	}
	
	/**
	 * 操作记录结果
	 * @author admin
	 *
	 */
	public class OperationLogResult{
		/**
		 * 没有处理结果
		 */
		public final static int NONE_PROCESS=-1;
		/**
		 * 处理中
		 */
		public final static int PROCESSING=0;
		/**
		 * 成功
		 */
		public final static int SUCCESS=1;
		/**
		 * 失败
		 */
		public final static int FAILED=2;
	}
	
	public class Usergroup{
		/**
		 * 超级管理员
		 */
		public final static int ADMIN=1;
		/**
		 * 普通管理员
		 */
		public final static int STAFF=2;
		/**
		 * 供货商
		 */
		public final static int SUPPLY=3;
		/**
		 * 一级商户
		 */
		public final static int GRADE_ONE=4;
		/**
		 * 二级商户
		 */
		public final static int GRADE_TWO=5;
		/**
		 * 二级商户(批量用)
		 */
		public final static int GRADE_TWO_2=6;
	}
	
	public class GroupType{
		/**
		 * 超级管理员
		 */
		public final static int ADMIN=1;
		/**
		 * 普通管理员
		 */
		public final static int STAFF=2;
		/**
		 * 供货商
		 */
		public final static int SUPPLY=3;
		/**
		 * 一级商户
		 */
		public final static int GRADE_ONE=4;
		/**
		 * 二级商户
		 */
		public final static int GRADE_TWO=5;
		/**
		 * 二级商户(批量用)
		 */
		public final static int GRADE_TWO_2=6;
	}
	
	public class GoodsFlag{
		/**
		 * 空
		 */
		public final static int NONE=0;
		/**
		 * 移动空中充值
		 */
		public final static int MOBILE_RECHARGE=10;
		/**
		 * 电信空中充值
		 */
		public final static int TELECOM_RECHARGE=11;
		/**
		 * 联通空中充值
		 */
		public final static int UNICOM_RECHARGE=12;
		/**
		 * 游戏直充
		 */
		public final static int GAME_RECHARGE=13;
		/**
		 * 游戏点卡
		 */
		public final static int GAME_CARD=20;
		/**
		 * 电信单一缴费
		 */
		public final static int TELECOM_SINGLE_RECHARGE=30;
		/**
		 * 电信宽带缴费
		 */
		public final static int TELECOM_BOARDBAND_RECHARGE=31;
		/**
		 * 电信综合缴费
		 */
		public final static int TELECOM_UNION_RECHARGE=32;
	}
	
	public class UseraccountResult{
		//返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		public final static int SUCCESS=0;
		
		public final static int NULL=1;
		
		public final static int FAIL=2;
		
		public final static int WRONG_PASSWORD=3;
	}
	
	public class UseraccountdealdetailFlag{
		/**
		 * 商户充值
		 */
		public final static int USER_DEPOSIT = 2;
		/**
		 * 划款
		 */
		public final static int REMIT = 1;
		/**
		 * 消费
		 */
		public final static int PURCHASE = 3;
		/**
		 * 冲正
		 */
		public final static int REVERSAL = 4;
		/**
		 * 提款
		 */
		public final static int DRAWING = 5;
		/**
		 * 被提款
		 */
		public final static int DRAWN = 6;
		/**
		 * 想供货商充值
		 */
		public final static int SUPPLY_DEPOSIT = 7;
		/**
		 * 提取佣金
		 */
		public final static int DRAW_COMMISSION = 8;
		/**
		 * 扣手续费
		 */
		public final static int CROSS_CLAIM = 9;
		/**
		 * 加手续费
		 */
		public final static int PLUS_CLAIM = 10;

		/**
		 * 埋数
		 */
		public final static int CHECK_OUT = 100;
		/**
		 * 用户取消
		 */
		public final static int USER_CANCEL=255;
		
	}
	
	public class UseraccountdealdetailLogFor{
		/**
		 * 供货商
		 */
		public final static int FOR_SUPPLY = 2;
		/**
		 * 商户
		 */
		public final static int FOR_USER = 1;
	}
	
	public class reverseResult{
		/**
		 * 处理中
		 */
		public final static int PROCESSING = 0; 
		/**
		 * 失败
		 */
		public final static int FAILED = -1;
		/**
		 * 成功
		 */
		public final static int SUCCESS = 1;
		/**
		 * 联系客服
		 */
		public final static int CONTACT_US = 2;
		/**
		 * 成功
		 */
		public final static int PROCESSED = 3;
	}
	
	/**
	 * 用户标记
	 * @author admin
	 *
	 */
	public class UserFlag{
		/**
		 * 正常
		 */
		public final static int NORMAL = 0;
		/**
		 * 冻结
		 */
		public final static int FREEZE = 1;
		/**
		 * 禁用
		 */
		public final static int FORBIDDEN = 2;

	}
}
