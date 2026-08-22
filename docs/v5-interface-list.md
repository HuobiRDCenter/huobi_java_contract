# 合约 V5 接口交付清单（55 个）

> 分支：`develop_H408547`（commit 816e7de，2026-08-22）
> 实测口径：带真实 API key 打 HTX 生产 API，非 mock。REST 私有接口真下单/撤单，WS 真订阅/触发推送。
> 实测日期：2026-08-22。**55 接口全部实测通过。**
>
> 文档链接：每个接口指向 HTX opend 官方文档页面（`https://www.htx.com/zh-cn/opend/newApiPages/?id=<UUID>`）。
>
> 老接口（非 V5）说明：SDK 仍含老 v3 接口（`/linear-swap-api/...`、期权等），这些是历史存量债，在统一/多资产保证金账户下不可用（报 6002/1051/1066），不在本 55 个 V5 交付范围内。

## 总览

| 类别 | 接口数 | 实测通过 | 需 key | 说明 |
|---|---|---|---|---|
| REST account | 6 | 6 ✅ | 是 | 账户余额/资产模式/费抵币种/流水 |
| REST trade | 13 | 13 ✅ | 是 | 下单/撤单/全平/查询委托与成交 |
| REST position | 7 | 7 ✅ | 是 | 杠杆/模式/风险限额/保证金 |
| REST market | 12 | 12 ✅ | 否 | 公开行情，无需 key |
| REST algo | 5 | 5 ✅ | 是 | 策略委托下单/撤/查 |
| WS 推送 | 7 | 7 ✅ | 是 | 订单/成交/持仓/账户推送 |
| WS 交易 | 5 | 5 ✅ | 是 | WS 下单/撤单 |
| **合计** | **55** | **55 ✅** | | |

> WS 12 接口全量回归 5/5 稳定通过（已修连接泄漏 + 等订阅 ack 再触发的 flaky，commit 816e7de）。

---

## REST account（6 个，私有）

| # | method | path | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|---|
| 1 | GET | `/v5/account/balance` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-195703a12d5) | `AccountAPITest#getContractAccountBalance` | ✅ |
| 2 | POST | `/v5/account/asset_mode` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957dd1a995) | `AccountAPITest#setAssetMode` | ✅ |
| 3 | GET | `/v5/account/asset_mode` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957dd37de0) | `AccountAPITest#getAssetMode` | ✅ |
| 4 | POST | `/v5/account/fee_deduction_currency` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-198826314c7) | `AccountAPITest#setAccountFeeDeductionCurrency` | ✅ |
| 5 | GET | `/v5/account/fee_deduction_currency` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b723f5241) | `AccountAPITest#getAccountFeeDeductionCurrency` | ✅ |
| 6 | GET | `/v5/account/bills` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b724157a2) | `AccountAPITest#getAccountBills` | ✅ |

## REST trade（13 个，私有）

| # | method | path | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|---|
| 7 | POST | `/v5/trade/order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957dd521e6) | `TradeAPITest#tradeOrderResponse` | ✅ |
| 8 | POST | `/v5/trade/batch_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957de4ff8f) | `TradeAPITest#tradeBachOrder` | ✅ |
| 9 | POST | `/v5/trade/cancel_order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957defffc6) | `TradeAPITest#cancelTradeOrder` | ✅ |
| 10 | POST | `/v5/trade/cancel_batch_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957df2abbe) | `TradeAPITest#cannelTradeBatchOrderResponse` | ✅ |
| 11 | POST | `/v5/trade/cancel_all_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957df6feb3) | `TradeAPITest#cannelTradeAllOrderResponse` | ✅ |
| 12 | POST | `/v5/trade/position` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957e02b018) | `TradeAPITest#tradePositionResponse` | ✅ |
| 13 | POST | `/v5/trade/position_all` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957e05d40c) | `TradeAPITest#tradePositionAllResponse` | ✅ |
| 14 | GET | `/v5/trade/order/opens` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957e082f23) | `TradeAPITest#tradeOrderOpensResponse` | ✅ |
| 15 | GET | `/v5/trade/order/details` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957e2a0e6a) | `TradeAPITest#tradeOrderTradesResponse` | ✅ |
| 16 | GET | `/v5/trade/order/history` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957efb2139) | `TradeAPITest#tradeOrderHistoryResponse` | ✅ |
| 17 | GET | `/v5/trade/order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-196a35af40e) | `TradeAPITest#getTradeOrderResponse` | ✅ |
| 18 | POST | `/v5/trade/cancel-after` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b72307cbb) | `TradeAPITest#tradeCancelAfterResponse` | ✅ |
| 19 | GET | `/v5/trade/position/opens` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957f1fbee4) | `TradeAPITest#tradePositionOpensResponse` | ✅ |

## REST position（7 个，私有）

| # | method | path | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|---|
| 20 | GET | `/v5/position/lever` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957f316730) | `TradeAPITest#positionLeverResponse` | ✅ |
| 21 | POST | `/v5/position/lever` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957f4a3b67) | `TradeAPITest#setPositionLeverResponse` | ✅ |
| 22 | GET | `/v5/position/mode` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957f4d93fd) | `TradeAPITest#positionModeResponse` | ✅ |
| 23 | POST | `/v5/position/mode` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957f4ec40b) | `TradeAPITest#setPositionModeResponse` | ✅ |
| 24 | GET | `/v5/position/risk/limit` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-1957f50bd39) | `TradeAPITest#positionRiskLimitResponse` | ✅ |
| 25 | GET | `/v5/position/risk/limit_tier` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b4528fc88) | `TradeAPITest#positionRiskLimitTierResponse` | ✅ |
| 26 | POST | `/v5/position/margin` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b7247e4dd) | `TradeAPITest#positionMarginResponse` | ✅ |

## REST market（12 个，公开，无需 key）

| # | method | path | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|---|
| 27 | GET | `/v5/market/risk/limit` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-195809dc1e8) | `MarketAPITest#marketRiskLimitResponse` | ✅ |
| 28 | GET | `/v5/market/assets_deduction_currency` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19580a209a2) | `MarketAPITest#assetsDeductionCurrencyResponse` | ✅ |
| 29 | GET | `/v5/market/multi_assets_margin` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19580a32ea2) | `MarketAPITest#marketMultiAssetsMarginListResponse` | ✅ |
| 30 | GET | `/v5/market/funding_rate` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b738520e0) | `MarketAPITest#getFundingRate` | ✅ |
| 31 | GET | `/v5/market/funding_rate_history` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b7389e8ee) | `MarketAPITest#getFundingRateHistory` | ✅ |
| 32 | GET | `/v5/market/open_interest` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73998b8f) | `MarketAPITest#getOpenInterest` | ✅ |
| 33 | GET | `/v5/market/price_limit` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73a247e7) | `MarketAPITest#getPriceLimit` | ✅ |
| 34 | GET | `/v5/market/liquidation_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73ab3d66) | `MarketAPITest#getLiquidationOrders` | ✅ |
| 35 | GET | `/v5/market/settlement_history` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73b16e57) | `MarketAPITest#getSettlementHistory` | ✅ |
| 36 | GET | `/v5/market/elite_account_ratio` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73bd8472) | `MarketAPITest#getEliteAccountRatio` | ✅ |
| 37 | GET | `/v5/market/elite_position_ratio` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73c012ca) | `MarketAPITest#getElitePositionRatio` | ✅ |
| 38 | GET | `/v5/market/estimated_settlement_price` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73c587fc) | `MarketAPITest#getEstimatedSettlementPrice` | ✅ |

## REST algo（5 个，私有）

| # | method | path | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|---|
| 39 | POST | `/v5/algo/order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b72756dd7) | `SrategyAPITest#algoOrder` | ✅ |
| 40 | POST | `/v5/algo/cancel_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73242ab0) | `SrategyAPITest#cancelAlgoOrder` | ✅ |
| 41 | GET | `/v5/algo/order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b73278ea1) | `SrategyAPITest#queryAlgoOrder` | ✅ |
| 42 | GET | `/v5/algo/order/opens` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b7345a20e) | `SrategyAPITest#queryOpenAlgoOrders` | ✅ |
| 43 | GET | `/v5/algo/order/history` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b7361ac19) | `SrategyAPITest#queryAlgoOrderHistory` | ✅ |

## WS 推送类（7 个，私有，需 key 鉴权）

> WS 推送地址：`wss://api.hbdm.com/ws/v5/notification`
> 文档列的 path 为空（WS 页面用 topic 而非 REST path），链接指向对应 opend 页。

| # | topic | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|
| 44 | `orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19580a58ac9) | `WssV5NotificationSubTest#testSubOrders` | ✅ |
| 45 | `trade` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19582b9e8db) | `WssV5NotificationSubTest#testSubTrade` | ✅ |
| 46 | `trade_detail` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19f3fb0ed2d) | `WssV5NotificationSubTest#testSubTradeDetail` | ✅ |
| 47 | `positions` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19582def41f) | `WssV5NotificationSubTest#testSubPositions` | ✅ |
| 48 | `account` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19583056529) | `WssV5NotificationSubTest#testSubAccount` | ✅ |
| 49 | `match_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19710f62bf5) | `WssV5NotificationSubTest#testSubMatchOrders` | ✅ |
| 50 | `algo_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-19b981e8c47) | `WssV5NotificationSubTest#testSubAlgoOrders` | ✅ |

## WS 交易类（5 个，私有，需 key 鉴权）

> WS 交易地址：`wss://api.hbdm.com/linear-swap-trade`
> 通过 op 指令下单/撤单，应答靠 cid 关联（无 op/topic 字段）。

| # | op | 文档链接 | 测试方法 | 实测 |
|---|---|---|---|---|
| 51 | `place_order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-196a85f248b) | `WssTradeTest#testPlaceOrder` | ✅ |
| 52 | `place_batch_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-196a951ac2c) | `WssTradeTest#testPlaceBatchOrders` | ✅ |
| 53 | `cancel_order` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-196a96db3fa) | `WssTradeTest#testCancelOrder` | ✅ |
| 54 | `cancel_batch_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-196a97b35b6) | `WssTradeTest#testCancelBatchOrders` | ✅ |
| 55 | `cancel_all_orders` | [opend](https://www.htx.com/zh-cn/opend/newApiPages/?id=8cb89359-77b5-11ed-9966-196a98d805e) | `WssTradeTest#testCancelAllOrders` | ✅ |

---

## 源码位置

- **REST service**：`src/main/java/com/huobi/api/service/usdt/{account,trade,algo}/`
  - account → `AccountAPIServiceImpl`
  - trade/position → `TradeAPIServiceImpl`
  - algo → `AlgoAPIServiceImpl`
  - market 公开行情 → `MarketAPIServiceImpl` / `ReferenceAPIServiceImpl`
- **REST 端点常量**：`src/main/java/com/huobi/api/constants/HuobiFutureAPIConstants.java`（V5 path 集中定义）
- **WS handler**：`src/main/java/com/huobi/wss/handle/`
  - 推送 → `WssV5NotificationHandle`（`wss://api.hbdm.com/ws/v5/notification`）
  - 交易 → `WssTradeHandle`（`wss://api.hbdm.com/linear-swap-trade`）
- **测试类**：`src/test/java/com/huobi/usdt/`
  - REST → `api/{AccountAPITest,TradeAPITest,SrategyAPITest,MarketAPITest}.java`
  - WS → `wss/{WssV5NotificationSubTest,WssTradeTest}.java`

## 复跑命令

带真实 key 跑 V5 全量回归（Java 8 + 阿里云镜像 + Clash 代理）：

```bash
source /tmp/htx_keys.env
JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home \
  mvn -s /tmp/aliyun.xml \
  -Dtest='com.huobi.usdt.api.TradeAPITest,com.huobi.usdt.api.SrategyAPITest,com.huobi.usdt.api.MarketAPITest,com.huobi.usdt.api.AccountAPITest,WssTradeTest,WssV5NotificationSubTest' \
  -DfailIfNoTests=false -Dsurefire.timeout=600 test \
  -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=7897 \
  -Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=7897
```

V5 接口全绿；同测试类里的老 v3 方法（`swap*`/`getSwap*`/`option*`）失败属预期存量债，不在 55 个 V5 范围内。
