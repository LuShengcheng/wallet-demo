# demo

### POST 用户注册

```url
http://localhost:9999/api/wallet/user
```

```json
{
    "success": true,
    "code": 200,
    "msg": "注册成功！",
    "response": {
        "userId": 1,
        "name": "lsc",
        "account": {
            "accountId": 1,
            "balanceFee": 0.0
        }
    }
}
```



#### 请求体：

```json
{
    "username":"lsc"
}
```

### POST 消费支付

```
http://localhost:9999/api/wallet/payment/pay
```

请求体：

```json
{
    "paymentOrderNo":4,
    "paymentAmount":100,
    "paymentAccountId":1,
    "paymentType":"WALLET"
}
```

#### 响应内容

```json
{
    "success": true,
    "code": 0,
    "msg": "支付成功"
}
```



### POST 退款

```
http://localhost:9999/api/wallet/payment/pay/refund
```

请求体：

```json
{
    "paymentOrderNo":1,
    "refundAmount":"1",
    "refundReason":"test",
    "refundRemark":"test"
}
```

#### 响应内容

```
	{
    "success": true,
    "code": 0,
    "msg": "操作成功"
}
```



### POST充值

```
http://localhost:9999/api/wallet/recharge
```

#### 请求体：

```json
{
    "orderId":1,
    "accountId":1,
    "amount":50,
    "channel":"BANK"
}
```

#### 响应内容

```json
{
    "success": true,
    "code": 0,
    "msg": "充值成功！"
}
```
### 查询消费订单

```
http://localhost:9999/api/wallet/payment/pay/{accountId}?page=1&size=10
```
```
http://localhost:9999/api/wallet/payment/pay/1?page=1&size=10
```
#### 响应内容
```json
{
    "success": true,
    "code": 200,
    "msg": "查询成功！",
    "response": [
        {
            "paymentId": 1,
            "paymentOrderNo": 4,
            "serialNumber": "3957ac0d56454230a38914f43404b809",
            "paymentAmount": 100.00,
            "hasRefund": false,
            "refundAmount": null,
            "paymentAccountId": 1,
            "paymentType": "WALLET",
            "paymentStatus": "PAID",
            "remark": null,
            "createTime": "2023-03-13 15:37:48 "
        }
    ],
    "count": 1
}
```

### GET 提现记录

```
http://localhost:9999/api/wallet/withdraw/{accountid}?page=1&size=10
```



```
http://localhost:9999/api/wallet/withdraw/1?page=1&size=10
```

```
{
    "success": true,
    "code": 200,
    "msg": "查询成功",
    "response": [
        {
            "id": 1,
            "serialNumber": "57219b9ab78344ba9fe993121eea7b51",
            "withdrawWay": "银行",
            "withdrawAccount": "123456789",
            "amount": 51.00,
            "createTime": "2023-03-13T07:04:44.373+00:00"
        },
        {
            "id": 2,
            "serialNumber": "01aac7296f92425ab8924da442354955",
            "withdrawWay": "银行",
            "withdrawAccount": "123456789",
            "amount": 54.00,
            "createTime": "2023-03-13T07:05:49.173+00:00"
        }
    ],
    "count": 2
}
```



### GET 查询充值记录

```
http://localhost:9999/api/wallet/recharge/{accountid}?page=2&size=10
```



```url
http://localhost:9999/api/wallet/recharge/1?page=2&size=10
```

#### 响应内容

```json

{
    "success": true,
    "code": 200,
    "msg": "查询成功！",
    "response": [
        {
            "id": 1,
            "orderId": 1,
            "amount": 50.00,
            "channel": "支付宝",
            "status": true,
            "createTime": "2023-03-13 14:35:41 "
        },
        {
            "id": 2,
            "orderId": 2,
            "amount": 50.00,
            "channel": "银行",
            "status": true,
            "createTime": "2023-03-13 14:36:04 "
        }
    ],
    "count": 2
}
```



### GET 查询钱包变动流水明细

```
http://localhost:9999/api/wallet/wallet-log/{account}?page=1&size=10
```



```
http://localhost:9999/api/wallet/wallet-log/1?page=1&size=10
```

#### 响应内容

```json
{
    "success": true,
    "code": 200,
    "msg": "查询成功！",
    "response": [
        {
            "id": 1,
            "serialNumber": "e14c8085f4014ece97f230f6c9f7d6fb",
            "targetType": "支付宝",
            "actionType": "充值",
            "amount": 50.00,
            "createTime": "2023-03-13 14:35:41 "
        },
        {
            "id": 2,
            "serialNumber": "629e5a1b20154356be5779263f96d364",
            "targetType": "银行",
            "actionType": "充值",
            "amount": 50.00,
            "createTime": "2023-03-13 14:36:04 "
        },
        {
            "id": 3,
            "serialNumber": "9c71d38838384086b40a1e1cfd2ea3c5",
            "targetType": "钱包",
            "actionType": "订单支付",
            "amount": 50.00,
            "createTime": "2023-03-13 14:37:08 "
        }
    ],
    "count": 3
}
```



### GET查询用户余额

```
http://localhost:9999/api/wallet/user?userid=1
```

#### 响应内容

```json
{
    "success": true,
    "code": 200,
    "msg": "查询成功",
    "response": {
        "userId": 1,
        "name": "lsc",
        "account": {
            "accountId": 1,
            "balanceFee": 0.00
        }
    }
}
```

