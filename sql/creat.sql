drop table if exists t_account;
create table t_account
(
    account_id  bigint auto_increment primary key,
    balance_fee decimal(38, 2) null comment '账户余额',
    update_time datetime(6)    null comment '更新时间',
    create_time datetime(6)    not null,
    remark      text           null comment '备注'
) comment '账户表' collate = utf8mb4_unicode_520_ci;

drop table if exists t_payment;
create table t_payment
(
    payment_id         bigint auto_increment comment '支付id' primary key,
    serial_number      varchar(255)   null comment '流水号',
    payment_account_id bigint         null comment '支付账户',
    payment_amount     decimal(38, 2) null comment '支付金额',
    payment_order_no   bigint         null comment '支付订单编号',
    payment_status     smallint       null comment '支付状态(0:支付完成 1:未支付 2:支付失败 3:退款)',
    payment_type       smallint       null comment '支付类型(0:支付宝 1:微信 2:银行 3:钱包)',
    has_refund         bit            null comment '是否退款',
    refund_amount      decimal(38, 2) null comment '退款金额',
    create_time        datetime(6)    not null comment '创建时间',
    update_time        datetime(6)    null comment '更新时间',
    remark             text           null comment '备注',
    constraint UK_35yt796i7ohmak8mga4h7ykex unique (payment_order_no),
    constraint UK_mpryyc3gxm6pdhwpu7xtk3jff unique (serial_number)
) comment '支付记录表' collate = utf8mb4_unicode_520_ci;

drop table if exists t_recharge_order;
create table t_recharge_order
(
    id          bigint auto_increment comment 'id' primary key,
    order_id    bigint         null comment '订单编号',
    account_id  bigint         null comment '账户id',
    amount      decimal(38, 2) null comment '充值金额',
    channel     smallint       not null comment '充值渠道',
    status      bit            null comment '充值状态',
    create_time datetime(6)    not null comment '创建时间',
    update_time datetime(6)    null comment '更新时间',
    remark      text           null comment '备注'
) comment '充值记录表' collate = utf8mb4_unicode_520_ci;

drop table if exists t_user;
create table t_user
(
    user_id     bigint auto_increment comment '用户id' primary key,
    account_id  bigint      null comment '账户id',
    name        varchar(64) null comment '姓名',
    create_time datetime(6) not null comment '创建时间',
    update_time datetime(6) null comment '更新时间',
    remark      text        null comment '备注',
    constraint FK5aviawdfo4t0o22qosg68ntav foreign key (account_id) references t_account (account_id)
) collate = utf8mb4_unicode_520_ci;

drop table if exists t_wallet_log;
create table t_wallet_log
(
    id            bigint auto_increment comment 'id' primary key,
    serial_number varchar(255)   null comment '流水号',
    account_id    bigint         null comment '账户id',
    action_type   smallint       null comment '操作类型(0:充值 1:提现 2:下单支付 3:退款)',
    amount        decimal(38, 2) null comment '金额',
    target_type   smallint       null comment '渠道(0:支付宝 1:微信 2:银行 )',
    create_time   datetime(6)    not null comment '创建时间',
    update_time   datetime(6)    null comment '更新时间',
    remark        text           null comment '备注'
) comment '钱包流水' collate = utf8mb4_unicode_520_ci;
drop table if exists t_withdraw_cash;
create table t_withdraw_cash
(
    id               bigint auto_increment primary key,
    serial_number    varchar(255)   null comment '流水号',
    account_id       bigint         null comment '账户id',
    amount           decimal(38, 2) null comment '提现金额',
    withdraw_account varchar(255)   not null comment '提现账户',
    withdraw_way     smallint       null comment '提现方式(0:支付宝 1:微信 2:银行)',
    create_time      datetime(6)    not null comment '创建时间',
    update_time      datetime(6)    null comment '更新时间',
    remark           text           null comment '备注'
) comment '提现记录表' collate = utf8mb4_unicode_520_ci;