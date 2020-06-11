只做登录

#### 账号密码登录
```
curl -v 'localhost:7002/login' -d 'username=admin&password=admin'
```
#### sms 登录
```
curl -v 'localhost:7002/sms-code?phone=123456'
```
```
curl -v 'localhost:7002/sms-login' -d 'phone=123456&code=778376'
```
#### 登录后调用接口    
```
curl -v -H 'X-Auth-Token:76aa9996-54b4-4f73-a88b-e00e8d79af37' 'localhost:7002/status'
```
