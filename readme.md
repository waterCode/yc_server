#接口文档
##登陆接口
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
token加密字符串，以后每次请求特别是涉及权限都要，role表示权限，
superAdmin：最高级管理员，拥有分配权限功能
admin：没有分配账号权限功能，但有评分功能
