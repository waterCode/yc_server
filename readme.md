## 接口文档 ##
ip为：47.106.105.117
## 登陆接口 ##
请求地址：http://ip:8083/login
发送数据如下
```
{
	"userName" : "admin",
	"password" : "admin"
}
```
返回
```json

 {
   "result": true,
   "message": "登录成功",
   "userName": "admin",
   "role": "superAdmin",
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoidXNlciIsImlhdCI6MTUzMTgzMTIzNX0.K6T7VJwosbyxKshl3g3_9HocQZSUVYXH7CoHAsVXhIc"
 }
```
token加密字符串，以后每次请求特别是涉及权限都要。
role表示权限：
    superAdmin：最高级管理员，拥有分配权限功能
    admin：没有分配账号权限功能，但有评分功能
    member：普通成员，没有任何权限
userName表示用户名字
result：true表示用户密码正确，false表示用户密码错误


## 加入我们接口 ##
请求地址：http://ip:8083/team/joinUs
```
              joinerName:'',//加入者名字
              college:'',//大学
              studentNum:'',//学号
              className:'',//班级名字
              qqNum:'',//qq号
              qqEmail:'',//email
              telephone:'',//电话
              groupName:'',//加入组别名字
              introduceDescription:'',//自我介绍
              skill:'',//技能
              experience:'',//经验
              award:'',//奖项
              isAdjust:false//是否接受调剂
```
字段如上，
返回如下
```
{
  "result": true,
  "message": "提交成功"
}
```
如果提交的学号与服务器保存数据相同，则返回该学生已经提交报名表


## 竞赛报名提交报名表接口 ##
地址：http://ip:8083/team/joinCompetitionAll
数据提交类似这样。文件按顺序提交，分别表示头像，附件1和附件2
```
let data = new FormData();
        data.append('uploadfile', this.formdataPic)
        data.append('uploadfile', this.formdataWenjian1)
        data.append('uploadfile', this.formdataWenjian2)
        data.append("captionName", this.competitionForm.captionName)
        data.append("zhuanYe", this.competitionForm.zhuanYe)
        data.append("xueHao", this.competitionForm.xueHao)
        data.append("telephone", this.competitionForm.telephone)
        data.append("weChat", this.competitionForm.weChat)
        data.append("school", this.competitionForm.school)
        data.append("duiWuName", this.competitionForm.duiWuName)
        data.append("zuoPinName", this.competitionForm.zuoPinName)
        data.append("aboutTest", this.competitionForm.aboutTest)
        data.append("aboutFunction", this.competitionForm.aboutFunction)
        data.append("aboutNews", this.competitionForm.aboutNews)
        data.append("technologyWay", this.competitionForm.technologyWay)
        data.append("technologyCase", this.competitionForm.technologyCase)
        data.append("productIntroduce", this.competitionForm.productIntroduce)
        data.append("adress", this.competitionForm.adress)
        data.append("teamMateOneName", this.competitionForm.teamMateOneName)
        data.append("teamMateOneClass", this.competitionForm.teamMateOneClass)
        data.append("teamMateOneTelephone", this.competitionForm.teamMateOneTelephone)

        data.append("teamMateTwoName", this.competitionForm.teamMateTwoName)
        data.append("teamMateTwoClass", this.competitionForm.teamMateTwoClass)
        data.append("teamMateTwoTelephone", this.competitionForm.teamMateTwoTelephone)

        data.append("teamMateThreeName", this.competitionForm.teamMateThreeName)
        data.append("teamMateThreeClass", this.competitionForm.teamMateThreeClass)
        data.append("teamMateThreeTelephone", this.competitionForm.teamMateThreeTelephone)

        data.append("teamMateFourName", this.competitionForm.teamMateFourName)
        data.append("teamMateFourClass", this.competitionForm.teamMateFourClass)
        data.append("teamMateFourTelephone", this.competitionForm.teamMateFourTelephone)
```
## 管理员相关接口 ##
### 获取所有成员列表 ###
请求地址：http://ip:8083/secure/users
必须带上token
该接口只有superAdmin才会有数据返回,如下
```
{
  "result": true,
  "message": "请求成功",
  "users": [
    {
      "id": 47,
      "userName": "tlm",
      "password": "zmc",
      "roles": "admin",
      "enabled": true
    },
    {
      "id": 46,
      "userName": "zmc",
      "password": "zmc",
      "roles": "admin",
      "enabled": true
    }
  ]
}
```

### 获取所有竞赛成员列表接口 ###
请求地址：http://ip:8083/secure/participants
header必须带上token
只有管理员有权限

### 获取某位竞赛成员详细信息
请求地址：http://ip:8083/secure/getRegistrationForm？id=xxx
请求方法：get
header必须带上token
只有管理员有权限