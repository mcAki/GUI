package com.sys.volunteer.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.StaticContentLoader;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.apache.bcel.internal.generic.NEW;
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
	 * 广播表session标记
	 */
	public final static String BROADCAST_SESSION_KEY="broadcast";
	/**
	 * 服务器IDsession标记
	 */
	public final static String SERVERID_SESSION_KEY="server";
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
	
	public final static int MISSION_TYPE_SCIENCE=1;
	public final static int MISSION_TYPE_HEALTH=2;
	public final static int MISSION_TYPE_STUDY=3;
	public final static int MISSION_TYPE_ETIQUETTE=4;
	public final static int MISSION_TYPE_COMMONWEAL=5;
	public final static int MISSION_TYPE_HONESTY=6;
	public final static int MISSION_TYPE_ENVIRONMENTALPROTECTION=7;
	/**
	 * (亚运)赛会
	 */
	public final static int MISSION_TYPE_ASIANGAMESCOMPITITION=8;
	/**
	 * (亚残)赛会
	 */
	public final static int MISSION_TYPE_ASIANPARAGAMESCOMPITITION=9;
	/**
	 * (亚运)城市文明
	 */
	public final static int MISSION_TYPE_ASIANGAMESCITYCULTURE=10;
	/**
	 * (亚运)城市站点
	 */
	public final static int MISSION_TYPE_ASIANGAMESCITYSITE=11;
	/**
	 * (亚残)城市文明
	 */
	public final static int MISSION_TYPE_ASIANPARAGAMESCITYCULTURE=12;
	/**
	 * (亚残)城市站点
	 */
	public final static int MISSION_TYPE_ASIANPARAGAMESCITYSITE=13;
	
	public final static int MISSIONVERIFICATION_PERMISSIBLE_PASS=1;
	public final static int MISSIONVERIFICATION_PERMISSIBLE_NOTPASS=2;
	public final static int MISSIONVERIFICATION_PERMISSIBLE_FORBIDDEN=1;
	/**
	 * 考勤时间保存成功的提示
	 */
	public final static int MISSION_SERVICELOG_CHECK_PASS=0;
	/**
	 * 考勤时间检查超过特定时长的提示
	 */
	public final static int MISSION_SERVICELOG_CHECK_LONG=1;

	/**
	 * 考勤状态错误
	 */
	public final static int MISSION_SERVICELOG_CHECK_STATE_WORNG=2;
	
	
	public final static String[] MISSION_TEAM_LEVEL_NAMES=new String[]{"子队","大队","中队","小队"};
	
	public final static String[] MISSION_TEAM_TYPE_NAMES=new String[]{"无效","一般","临时小队"};
	
	public final static String[] MISSION_VERIFICATION_PERMISSIBLE_NAMES=new String[]{"其他","审批通过","审批不通过","直接禁止本项目"};
	
	
	/**
	 * 团队建立岗位对接表--表头对照
	 */
	public final static String[] EXCEL_MISSIONPOSITION_HEADER=new String[]{"ID","总序号","业务口","岗位名称","姓名","性别","证件类型","证件号码",
	                                                                       "来源学校","联系电话","职务\n(可下拉)","所属中队编号","所属小队编号",
	                                                                       "大队代码\n(一个场馆一个大队)","中队代码\n(根据中队编号自动生成)","小队代码\n(根据小队编号自动生成)"};
	
	/**
	 * 短信邀请
	 */
	public final static int USER_SELECTION_SMS = 0;
	/**
	 * 网上报名
	 */
	public final static int USER_SELECTION_ONLINE = 1;
	/**
	 * 协议直接分配
	 */
	public final static int USER_SELECTION_PROTOCOL = 2;
	
	public final static String[] EXCEL_USERS_HEADER=new String[]{"打印PDF确认表编号", "注册编号", "中文姓",	"中文名",	"姓"	,"名",
		"出生国家/地区",	"证件所属国家/地区",	"性别", "出生日期",	"电子邮箱地址",	"曾用姓"	,"曾用名","惯用姓", "惯用名", "民族",	"身高(cm)",	
		"体重(KG)",	"鞋码",	"身体状况",	"保险受益人",	"与您的关系",	"户籍所在国家/地区",	"户籍所在省/自治区/直辖市	","户籍所在市/县",
		"户籍所在详细地址",	"证件类别",	"证件签发机购",	"证件号码",	"证件有效期截止日期","学校",	"哪个校区", "校区所在的区(市)",
		"院系",	"专业",	"班级",	"在读学历",	"学校详细地址",	"入学时间",	"预计毕业时间",	"现居地所在国家/地区",	"现居地所在省/自治区/直辖市",
		"现居地所在市/县",	"现居地所在区",	"现居地所在详细地址",	"实际工作单位/就读学校名称",	"工作单位/就读学校详细地址",	"目前的职业",
		"申请人移动电话",	"申请人固定电话",	"其他联系人姓名",	"其他联系人移动电话",	"语言",	"擅长外语", "来源单位",	"亚组委招募部门",
		"普通/专业志愿者","PDF确认表是否确认", "状态", "职位",	"场馆",	"业务口",	"是否上传照片"};

	/**
	 * 暂无识别(主要用于登陆时候作自动识别标记)
	 */
	public final static int USER_IDCARD_UNKNOW = 255;	
	/**
	 * 护照
	 */
	public final static int USER_IDCARD_PAS = 0;
	/**
	 * 中国居民身份证
	 */
	public final static int USER_IDCARD_CID = 1;

	/**
	 * 中国人民解放军军官证或士兵证
	 */
	public final static int USER_IDCARD_JGZ = 2;
	
	/**
	 * 中国武警证件
	 */
	public final static int USER_IDCARD_WJZ = 3;
	/**
	 * 台湾居民来往大陆通行证
	 */
	public final static int USER_IDCARD_TWT = 4;
	/**
	 * 港、澳居民来往内地通行证
	 */
	public final static int USER_IDCARD_GAT = 5;
	
	
	
	/**
	 * 公安现役警官证件或士兵证（边防系统）
	 */
	public final static int USER_IDCARD_GAB = 6;
	/**
	 * 公安现役警官证件或士兵证（警卫系统）
	 */
	public final static int USER_IDCARD_GAJ = 7;	
	/**
	 * 公安现役警官证件或士兵证（消防系统）
	 */
	public final static int USER_IDCARD_GAX = 8;


	
	/**
	 * 删除标记--未删除
	 */
	public final static int USER_IS_NOT_DELETE=0;
	
	/**
	 * 删除标记--已删除
	 */
	public final static int USER_IS_DELETE=1;
	
	
	/**
	 * 城市志愿者
	 */
	public final static String USER_COMEFROM_CITY="1";
	/**
	 * 赛会志愿者
	 */
	public final static String USER_COMEFROM_VENUN="2";
	/**
	 * 赛会与城市志愿者
	 */
	public final static String USER_COMEFROM_VENUN_AND_CITY="3";
	/**
	 * 未通过安检的志愿者
	 */
	public final static String USER_UNAPPROVESECURITYCHECK="0";
	 
	
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
	 * 用户状态--志愿者
	 */
	public final static int USER_STATE_VOLUNTEER=1;
	/**
	 * 用户状态--拟录用
	 */
	public final static int USER_STATE_PLAN_TO_HIRE=2;
	/**
	 * 用户状态--待录用
	 */
	public final static int USER_STATE_TREATS_HIRE=3;
	/**
	 * 用户状态--已录用
	 */
	public final static int USER_STATE_HIRE=4;
	
	
	
	/**
	 * 用户状态--启用
	 */
	public final static int USER_STATE_ENABLED=1;

	/**
	 * 用户状态--停用
	 */
	public final static int USER_STATE_DISABLED=2;
	
	/**
	 * 队伍类型--无效
	 */
	public final static int MISSION_TEAM_TYPE_INVALID = 0;
	
	/**
	 * 队伍类型--系统生成小队号
	 */
	public final static int MISSION_TEAM_TYPE_AUTO_TEAM = 1;
	
	/**
	 * 队伍类型--临时加班小队
	 */
	public final static int MISSION_TEAM_TYPE_TEMPORARY_TEAM =2;
	
	/**
	 * mission_team 队伍允许加入
	 */
	public final static int MISSION_TEAM_IS_ALLOW_JOIN = 0;
	/**
	 * mission_team 队伍不允许加入
	 */
	public final static int MISSION_TEAM_IS_NOT_ALLOW_JOIN =1;
	
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
	 * MissionPersonal考勤资格
	 * @author Administrator
	 *
	 */
	public static class MissionPersonal{
		/**
		 * 不允许考勤
		 */
		public final static int NORMAL_PERSON=0;
		/**
		 * 允许考勤
		 */
		public final static int IS_CHECK_ON_PERSON=1;
		/**
		 * 允许提交
		 */
		public final static int IS_COMMIT=255;
		/**
		 * 不确定
		 */
		public final static int  SELECTION_INDEFINITE=0;
		/**
		 * 已录用
		 */
		public final static int  SELECTION_EMPLOYED=1;
		/**
		 * 未录用
		 */
		public final static int  SELECTION_UNEMPLOYED=2;
		/**
		 * 待志愿者确认
		 */
		public final static int  SELECTION_WAITING=3;//
		/**
		 * 志愿者确认
		 */
		public final static int  SELECTION_SURE=4;//
		/**
		 * 志愿者拒绝
		 */
		public final static int  SELECTION_REJECT=5;//
		
		/**
		 * 已结项
		 */
		public final static int  SELECTION_FINISHED=100;//
		/**
		 * 志愿者退出
		 */
		public final static int  SELECTION_QUIT=255;//
		/**
		 * 志愿者被删除
		 */
		public final static int  SELECTION_DELETED=256;//
		
		public final static int RECRUIT_TYPE_SMS=0;
	}
	
	/**
	 * 队伍等级
	 * @author Administrator
	 *
	 */
	public static class MissionTeamLevel{
		/**
		 * 大队等级
		 */
		public final static int LV_GROUP=1;
		
		/**
		 * 中队等级
		 */
		public final static int LV_LOCHUS=2;
		
		/**
		 * 小队等级
		 */
		public final static int LV_TEAM=3;
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
	 * 任务职位
	 * @author Administrator
	 *
	 */
	public class MissionPosition{
		/**
		 * 未确定
		 */
		public final static int UNKNOWN=1;
		/**
		 * 负责人
		 */
		public final static int MANAGER=2;
		/**
		 * 考勤员
		 */
		public final static int TIMEKEEPER=3;
		/**
		 * 队员
		 */
		public final static int MEMBER=10;
		/**
		 * 小队长
		 */
		public final static int SQUAD_LEADER=11;
		/**
		 * 副中队长
		 */
		public final static int DEPUTY_LOCHUS_LEADER=12;
		/**
		 * 中队长
		 */
		public final static int LOCHUS_LEADER=13;
		/**
		 * 副大队长
		 */
		public final static int DEPUTY_GROUP_LEADER=14;
		/**
		 * 大队长
		 */
		public final static int GROUP_LEADER=15;
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
	 * 
	 * @author Administrator
	 *
	 */
	public class UserGroupConst{
		/**
		 * 正式志愿者
		 */
		public final static int USERSGROUP_VOLUNTEER=100;
		/**
		 * 超级管理员
		 */
		public final static int USERSGROUP_ADMINISTRATOR=1;
		/**
		 * 管理员
		 */
		public final static int USERSGROUP_MANAGER=2;
		/**
		 * 市级管理员
		 */
		public final static int USERSGROUP_CITY_MANAGER=11;
		/**
		 * 区级管理员
		 */
		public final static int USERSGROUP_DISTRICT_MANAGER=12;
		/**
		 * 街道管理员
		 */
		public final static int USERSGROUP_AVENUE_MANAGER=13;
		/**
		 * 话务员
		 */
		public final static int USERSGROUP_CUSTOMER_SERVICE=20;
		/**
		 * 申请中志愿者
		 */
		public final static int USERSGROUP_APPLYING_VOLUNTEER=91;
		/**
		 * 待录用志愿者
		 */
		public final static int USERSGROUP_WAITING_VERIFY_VOLUNTEER=92;
		/**
		 * 已审核志愿者
		 */
		public final static int USERSGROUP_VERIFIED_VOLUNTEER=93;
		/**
		 * 荣誉志愿者
		 */
		public final static int USERSGROUP_HONOR_VOLUNTEER=110;
		
	}
	
	/**
	 * 考勤请假旷工情况
	 */
	public class ServiceLog{
		/**
		 * 无
		 */
		public final static int NONE=0;
		/**
		 * 请假
		 */
		public final static int ASKOFF=1;
		/**
		 * 旷工
		 */
		public final static int NOATTENDANCE=2;
	}
	
	public class MissionType{
		
	}
}
