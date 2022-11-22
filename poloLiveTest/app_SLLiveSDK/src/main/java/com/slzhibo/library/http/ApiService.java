package com.slzhibo.library.http;



import androidx.annotation.NonNull;

import com.slzhibo.library.http.bean.HttpResultModel;
import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.model.*;
import com.slzhibo.library.model.cache.VersionCacheEntity;
import com.slzhibo.library.model.db.GiftBoxEntity;
//import com.slzhibo.library.model.p017db.GiftBoxEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
//import p481f.p482a.Observable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* loaded from: classes2.dex */
public interface ApiService {
    public static final String BASE_START_LIVE_NOTIFY_URL = "tl/live/startLiveNotify/";
    public static final String BASE_TL_ACTIVITY_URL = "tl/activity/";
    public static final String BASE_TL_ANCHOR_INCOME_SERVER_URL = "tl/statistic/mobile/anchor/income";
    public static final String BASE_TL_ANCHOR_PK_LM_URL = "tl/anchorPk/lianmai/";
    public static final String BASE_TL_ANCHOR_PK_URL = "tl/anchorPk/";
    public static final String BASE_TL_AUDIO_URL = "tl/audio/";
    public static final String BASE_TL_AUDIO_VIDEO_ROOM_ANCHOR_URL = "tl/audio/videoRoom/anchor/";
    public static final String BASE_TL_AUDIO_VIDEO_ROOM_URL = "tl/audio/videoRoom/";
    public static final String BASE_TL_AUDIO_VIDEO_ROOM_USER_URL = "tl/audio/videoRoom/user/";
    public static final String BASE_TL_AUDIO_VOICE_ROOM_ANCHOR_URL = "tl/audio/voiceRoom/anchor/";
    public static final String BASE_TL_AUDIO_VOICE_ROOM_URL = "tl/audio/voiceRoom/";
    public static final String BASE_TL_AUDIO_VOICE_ROOM_USER_URL = "tl/audio/voiceRoom/user/";
    public static final String BASE_TL_AUTH_URL = "tl/auth/";
    public static final String BASE_TL_AUTH_USER_SERVICE_INDEX_URL = "tl/auth/userService/mobile/index/";
    public static final String BASE_TL_BLUETOOTH_URL = "tl/activity/bluetooth/";
    public static final String BASE_TL_CLAN_URL = "tl/clan/clanAdmin/";
    public static final String BASE_TL_EXP_ROOM_ACTION = "tl/exp/room/action";
    public static final String BASE_TL_FOLLOW_SERVER_URL = "tl/follow/mobile/follow/";
    public static final String BASE_TL_GAME_URL = "tl/shop/game/";
    public static final String BASE_TL_GIFT_ANCHOR_INCOME_SERVER_URL = "tl/gift/mobile/anchor/income/";
    public static final String BASE_TL_GIFT_ANCHOR_SERVER_URL = "tl/gift/anchor/";
    public static final String BASE_TL_GIFT_SERVER_URL = "tl/gift/";
    public static final String BASE_TL_GUARD_ANCHOR_INCOME_SERVER_URL = "tl/guard/mobile/anchor/income/";
    public static final String BASE_TL_GUARD_SERVER_URL = "tl/guard/";
    public static final String BASE_TL_GUARD_V1_SERVER_URL = "tl/guard/v1/guard/";
    public static final String BASE_TL_IMPRESSION_URL = "tl/user/impression/";
    public static final String BASE_TL_INDEX_ADV_URL = "tl/index/adv/";
    public static final String BASE_TL_INDEX_CACHE_URL = "tl/index/cache/";
    public static final String BASE_TL_INDEX_LIVE_URL = "tl/index/live/";
    public static final String BASE_TL_INDEX_MOBILE_URL = "tl/index/mobile/";
    public static final String BASE_TL_INDEX_SEARCH_URL = "tl/index/search/";
    public static final String BASE_TL_INDEX_URL = "tl/index/";
    public static final String BASE_TL_INTIMATE_ANCHOR_URL = "tl/activity/intimate/anchor/";
    public static final String BASE_TL_INTIMATE_URL = "tl/activity/intimate/";
    public static final String BASE_TL_INTIMATE_USER_URL = "tl/activity/intimate/user/";
    public static final String BASE_TL_ITEM_GIFT_BOX_SERVER_URL = "tl/item/giftBox/";
    public static final String BASE_TL_ITEM_MOBILE_SERVER_URL = "tl/item/mobile/";
    public static final String BASE_TL_ITEM_PROPS_SERVER_URL = "tl/item/mobile/props/";
    public static final String BASE_TL_ITEM_SERVER_URL = "tl/item/";
    public static final String BASE_TL_ITEM_TASK_BOX_SERVER_URL = "tl/item/taskBox/";
    public static final String BASE_TL_ITEM_USER_PROPS_SERVER_URL = "tl/item/mobile/userProps/";
    public static final String BASE_TL_LIVE_CORE_MOBILE_LIVE_URL = "tl/liveCore/mobile/live/";
    public static final String BASE_TL_LIVE_CORE_URL = "tl/liveCore/";
    public static final String BASE_TL_LIVE_DRAW_URL = "tl/activity/liveDraw/";
    public static final String BASE_TL_LIVE_HISTORY_URL = "tl/liveHistory/";
    public static final String BASE_TL_LIVE_MANGE_MOBILE_MY_LIVE_URL = "tl/liveManage/mobile/myLive/";
    public static final String BASE_TL_LIVE_MANGE_URL = "tl/liveManage/";
    public static final String BASE_TL_LIVE_MOBILE_LIVE_URL = "tl/live/mobile/live/";
    public static final String BASE_TL_LIVE_MOBILE_MY_LIVE_URL = "tl/live/mobile/myLive/";
    public static final String BASE_TL_LIVE_MOBILE_URL = "tl/live/mobile/";
    public static final String BASE_TL_LIVE_TICKET_ROOM_URL = "tl/live/ticketRoom/";
    public static final String BASE_TL_LIVE_URL = "tl/live/";
    public static final String BASE_TL_MESSAGE_URL = "tl/activity/messageBox/";
    public static final String BASE_TL_MOBILE_STATISTICS_SERVER_URL = "tl/statistic/mobile/statistics/";
    public static final String BASE_TL_MOBILE_STATISTIC_SERVER_URL = "tl/statistic/mobile/";
    public static final String BASE_TL_NOBILITY_SERVER_URL = "tl/nobility/";
    public static final String BASE_TL_POPULARITY_CARD = "tl/shop/myPopularityCard/";
    public static final String BASE_TL_REPORT_SERVER_URL = "tl/offence/offenceContent/";
    public static final String BASE_TL_SHOP_CAR_SERVER_URL = "tl/shop/car/";
    public static final String BASE_TL_SHOP_SERVER_URL = "tl/shop/";
    public static final String BASE_TL_STATISTIC_SERVER_URL = "tl/statistic/";
    public static final String BASE_TL_STATISTIC_USER_EXPENSE_SERVER_URL = "tl/statistic/user/expense/";
    public static final String BASE_TL_STORE_ANCHOR_PRODUCT_ITEM_URL = "tl/store/anchor/productItem/";
    public static final String BASE_TL_STORE_ANCHOR_PRODUCT_URL = "tl/store/anchor/product/";
    public static final String BASE_TL_STORE_ANCHOR_URL = "tl/store/anchor/";
    public static final String BASE_TL_STORE_PRODUCT_URL = "tl/store/product/";
    public static final String BASE_TL_STORE_URL = "tl/store/";
    public static final String BASE_TL_STORE_USER_PRODUCT_URL = "tl/store/user/product/";
    public static final String BASE_TL_STORE_USER_URL = "tl/store/user/";
    public static final String BASE_TL_STREAM_SERVER_URL = "tl/streams/stream/";
    public static final String BASE_TL_TURNTABLE_SERVER_URL = "tl/turntable/";
    public static final String BASE_TL_URL = "tl/";
    public static final String BASE_TL_USER_ANCHOR_URL = "tl/user/anchor/";
    public static final String BASE_TL_USER_EXPENSE_SERVER_URL = "tl/statistic/mobile/user/expense";
    public static final String BASE_TL_USER_MOBILE_SERVER_URL = "tl/user/userService/mobile/";
    public static final String BASE_TL_USER_MOBILE_URL = "tl/user/mobile/";
    public static final String BASE_TL_USER_URL = "tl/user/";
    public static final String BASE_USER_CHAT_URL = "tl/chat/userMark/";
    public static final String BASE_USER_MARK_URL = "tl/user/userMark/";

    @POST("tl/activity/liveDraw/saveWinningUserAddress")
    Observable<HttpResultModel<Object>> addAddress(@Body Map<String, Object> map);

    @POST("tl/store/productItem/addWatchTimes")
    Observable<HttpResultModel<Object>> addWatchTimesReportService(@Body Map<String, Object> map);

    @POST("tl/activity/messageBox//addWinningMessage")
    Observable<HttpResultModel<Object>> addWinningMessage(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/intimateTaskList")
    Observable<HttpResultModel<List<QMInteractTaskEntity>>> anchorAcceptedTaskListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/intimateTaskList")
    Observable<HttpResultModel<HttpResultPageModel<QMInteractTaskEntity>>> anchorSelectTaskListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/showTask")
    Observable<HttpResultModel<QMInteractTaskListEntity>> anchorShowTaskListService(@Body Map<String, Object> map);

    @POST("tl/auth/quickLogin/authorizedLogin")
    Observable<HttpResultModel<FastLoginEntity>> authorizedLoginService(@Body Map<String, Object> map);

    @POST("tl/activity/bluetooth/countdown")
    Observable<HttpResultModel<Object>> bluetoothCountdownService(@Body Map<String, Object> map);

    @POST("tl/activity/bluetooth/send")
    Observable<HttpResultModel<LYDevEntity>> bluetoothSendService(@Body Map<String, Object> map);

    @POST("tl/activity/bluetooth/tease")
    Observable<HttpResultModel<Object>> bluetoothTeaseService(@Body Map<String, Object> map);

    @POST("tl/live/mobile/live/broadcastClickCountUpdate")
    Observable<HttpResultModel<Object>> broadcastClickCountUpdate(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/cancelAppeal")
    Observable<HttpResultModel<HttpResultPageModel<Object>>> cancelAppealInfoService(@Body Map<String, Object> map);

    @POST("tl/user/userMark/cancel")
    Observable<HttpResultModel<Object>> cancelWearCenterService(@Body Map<String, Object> map);

    @POST("tl/liveCore/mobile/live/submit/changeLiveStream")
    Observable<HttpResultModel<Object>> changeLiveStreamService(@Body Map<String, Object> map);

    @POST("tl/item/taskBox/finished")
    Observable<HttpResultModel<Object>> changeTaskState(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/chargeTaskDel")
    Observable<HttpResultModel<Object>> chargeTaskDelService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/chargeTaskList")
    Observable<HttpResultModel<List<QMInteractTaskEntity>>> chargeTaskListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/publish")
    Observable<HttpResultModel<Object>> chargeTaskPublishService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/chargeTaskUpdate")
    Observable<HttpResultModel<QMInteractTaskEntity>> chargeTaskUpdateService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/configGiftThreshold")
    Observable<HttpResultModel<Object>> configGiftThresholdService(@Body Map<String, Object> map);

    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@NonNull @Url String str);

    @POST("tl/user/userMark/equip")
    Observable<HttpResultModel<Object>> equipWearCenterService(@Body Map<String, Object> map);

    @POST("tl/shop/game/anchorIncome/list")
    Observable<HttpResultModel<HttpResultPageModel<GameEntity>>> gameAnchorIncomeListService(@Body Map<String, Object> map);

    @POST("tl/shop/game/anchorIncome")
    Observable<HttpResultModel<GameEntity>> gameAnchorIncomeService(@Body Map<String, Object> map);

    @POST("tl/shop/game/clanAdminIncome/list")
    Observable<HttpResultModel<HttpResultPageModel<GameEntity>>> gameClanAdminIncomeListService(@Body Map<String, Object> map);

    @POST("tl/shop/game/clanAdminIncome")
    Observable<HttpResultModel<GameEntity>> gameClanAdminIncomeService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/acceptInvitePk")
    Observable<HttpResultModel<Object>> getAcceptInvitePkService(@Body Map<String, Object> map);

    @POST("tl/achievement/wall")
    Observable<HttpResultModel<HttpResultPageModel<UserAchieveEntity>>> getAchieveWallService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/addBlack")
    Observable<HttpResultModel<Object>> getAddBlackService(@Body Map<String, Object> map);

    @POST("tl/live/mobile/live/addHerald")
    Observable<HttpResultModel<Object>> getAddLivePreNoticeService(@Body Map<String, Object> map);

    @POST("tl/index/live/list")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getAllListService(@Body Map<String, Object> map);

    @POST("tl/user/mobile/anchor/ispass")
    Observable<HttpResultModel<AnchorEntity>> getAnchorAuthService(@Body Map<String, Object> map);

    @POST("tl/liveHistory/liveFeedback/add")
    Observable<HttpResultModel<Object>> getAnchorFeedbackService(@Body Map<String, Object> map);

    @POST("tl/user/anchor/isFrozen")
    Observable<HttpResultModel<AnchorEntity>> getAnchorFrozenStatusService(@Body Map<String, Object> map);

    @POST("tl/guard/anchor/list")
    Observable<HttpResultModel<HttpResultPageModel<AnchorEntity>>> getAnchorGuardListService(@Body Map<String, Object> map);

    @POST("tl/user/anchor/info")
    Observable<HttpResultModel<AnchorEntity>> getAnchorInfoService(@Body Map<String, Object> map);

    @POST("tl/live/mobile/live/detail")
    Observable<HttpResultModel<LiveEndEntity>> getAnchorLiveDetailService(@Body Map<String, Object> map);

    @POST("tl/liveCore/mobile/live/startLive/submit")
    Observable<HttpResultModel<Object>> getAnchorStartLiveSubmitService(@Body Map<String, Object> map);

    @POST("tl/liveCore/mobile/live/startLive/verify")
    Observable<HttpResultModel<StartLiveVerifyEntity>> getAnchorStartPayLiveService(@Body Map<String, Object> map);

    @POST("tl/sysParam/appParamConfig/view")
    Observable<HttpResultModel<H5WebDataEntity>> getAppParamConfigService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/appealInfo")
    Observable<HttpResultModel<AppealInfoEntity>> getAppealInfoService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/appeal/list")
    Observable<HttpResultModel<HttpResultPageModel<AppealHistoryEntity>>> getAppealListService(@Body Map<String, Object> map);

    @POST("tl/follow/mobile/follow/followAnchorList")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getAttentionAnchorListService(@Body Map<String, Object> map);

    @POST("tl/follow/mobile/follow/addOrCancel")
    Observable<HttpResultModel<Object>> getAttentionAnchorService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/getUserWinningDetailById")
    Observable<HttpResultModel<AwardDetailEntity>> getAwardDetail(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/searchMyWinningRecodePageByUserId")
    Observable<HttpResultModel<HttpResultPageModel<AwardHistoryEntity>>> getAwardListService(@Body Map<String, Object> map);

    @POST("tl/user/userMark/badgeListForOwn")
    Observable<HttpResultModel<HttpResultPageModel<MedalEntity>>> getBadgeListForOwnService(@Body Map<String, Object> map);

    @POST("tl/user/userMark/badgeListForUsed")
    Observable<HttpResultModel<HttpResultPageModel<MedalEntity>>> getBadgeListForUsedService(@Body Map<String, Object> map);

    @POST("tl/liveManage/getBanPostUserIdAndLiveId")
    Observable<HttpResultModel<LiveItemEntity>> getBanPostService(@Body Map<String, Object> map);

    @POST("tl/liveManage/mobile/myLive/banPostSetting/list")
    Observable<HttpResultModel<HttpResultPageModel<BannedEntity>>> getBannedListService(@Body Map<String, Object> map);

    @POST("tl/liveManage/mobile/myLive/banPostSetting/banPost")
    Observable<HttpResultModel<Object>> getBannedSetService(@Body Map<String, Object> map);

    @POST("tl/index/adv/list")
    Observable<HttpResultModel<List<BannerEntity>>> getBannerListService(@Body Map<String, Object> map);

    @POST("tl/index/bluetoothList")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getBluetoothListTagListService(@Body Map<String, Object> map);

    @POST("tl/turntable/boomStatus")
    Observable<HttpResultModel<BoomStatusEntity>> getBoomStatusService(@Body Map<String, Object> map);

    @POST("tl/shop/car/buy")
    Observable<HttpResultModel<Object>> getBuyCarService(@Body Map<String, Object> map);

    @POST("tl/liveCore/user/buyLiveTicket")
    Observable<HttpResultModel<MyAccountEntity>> getBuyLiveTicketService(@Body Map<String, Object> map);

    @GET("tl/index/cache/version")
    Observable<HttpResultModel<VersionCacheEntity>> getCacheVersionService(@Query("appId") String str);

    @POST("tl/anchorPk/cancelInvitePK")
    Observable<HttpResultModel<Object>> getCancelInvitePkService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/cancelRandomMathPK")
    Observable<HttpResultModel<Object>> getCancelRandomMathPKService(@Body Map<String, Object> map);

    @POST("tl/shop/car/resource")
    Observable<HttpResultModel<List<CarDownloadEntity>>> getCarDownloadList(@Body Map<String, Object> map);

    @POST("tl/shop/car/record")
    Observable<HttpResultModel<List<CarHistoryRecordEntity>>> getCarHistoryRecordListService(@Body Map<String, Object> map);

    @POST("tl/shop/car/list")
    Observable<HttpResultModel<List<CarEntity>>> getCarListService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/statistics/income")
    Observable<HttpResultModel<List<AnchorEntity>>> getCharmTopListService(@Body Map<String, Object> map);

    @POST("tl/chat/userMark/chatPreview")
    Observable<HttpResultModel<ChatPreviewEntity>> getChatPreviewService(@Body Map<String, Object> map);

    @POST("tl/liveCore/user/checkTicket")
    Observable<HttpResultModel<CheckTicketEntity>> getCheckTicketService(@Body Map<String, Object> map);

    @POST("tl/index/adv/hits")
    Observable<HttpResultModel<Object>> getClickAdService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/config/get")
    Observable<HttpResultModel<PKConfigEntity>> getConfigInfoService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/config/update")
    Observable<HttpResultModel<Object>> getConfigUpdateService(@Body Map<String, Object> map);

    @POST("tl/user/sys/countryCode/findAllCountryCode")
    Observable<HttpResultModel<List<CountryCodeEntity>>> getCountryCodeService(@Body Map<String, Object> map);

    @POST("tl/user/userService/mobile/live/getCurrentOnlineUserList")
    Observable<HttpResultModel<OnLineUsersEntity>> getCurrentOnlineUserListService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/statistics/expendByAnchorId")
    Observable<HttpResultModel<List<AnchorEntity>>> getDedicateTopListService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/searchAnchorDrawResultByDrawId")
    Observable<HttpResultModel<HdDrawEndEntity>> getEndLiveDrawInfoService(@Body Map<String, Object> map);

    @POST("tl/live/enjoyList")
    Observable<HttpResultModel<List<LiveEntity>>> getEnjoyListService(@Body Map<String, Object> map);

    @POST("tl/nobility/enterHide/set")
    Observable<HttpResultModel<Object>> getEnterHideService(@Body Map<String, Object> map);

    @POST("tl/auth/userService/mobile/index/leaveLiveModule")
    Observable<HttpResultModel<Object>> getExitSDKService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/user/expense/car")
    Observable<HttpResultModel<HttpResultPageModel<ExpenseCarDetailEntity>>> getExpenseCarListService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/user/expense/gift")
    Observable<HttpResultModel<HttpResultPageModel<GiftIncomeExpenseDetail>>> getExpenseGiftListService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/user/expense/guard")
    Observable<HttpResultModel<HttpResultPageModel<GuardIncomeExpenseDetail>>> getExpenseGuardListService(@Body Map<String, Object> map);

    @POST("tl/statistic/user/expense/nobility")
    Observable<HttpResultModel<HttpResultPageModel<NobleIncomeExpenseEntity>>> getExpenseNobleListService(@Body Map<String, Object> map);

    @POST("tl/statistic/user/expense/liveTicket")
    Observable<HttpResultModel<HttpResultPageModel<PaidLiveIncomeExpenseEntity>>> getExpensePaidLiveService(@Body Map<String, Object> map);

    @POST("tl/store/user/product/expense")
    Observable<HttpResultModel<HttpResultPageModel<HJProductIncomeExpenseEntity>>> getExpenseProductService(@Body Map<String, Object> map);

    @POST("tl/item/mobile/props/expendRecords")
    Observable<HttpResultModel<HttpResultPageModel<PropsIncomeExpenseDetail>>> getExpensePropsListService(@Body Map<String, Object> map);

    @POST("tl/statistic/expense/prop")
    Observable<HttpResultModel<HttpResultPageModel<PropsIncomeExpenseDetail>>> getExpensePropsPriceListService(@Body Map<String, Object> map);

    @POST("tl/statistic/user/expense/score")
    Observable<HttpResultModel<HttpResultPageModel<ScoreGiftIncomeExpenseDetail>>> getExpenseScoreGiftListService(@Body Map<String, Object> map);

    @POST(BASE_TL_USER_EXPENSE_SERVER_URL)
    Observable<HttpResultModel<ExpenseMenuEntity>> getExpenseStatisticsService(@Body Map<String, Object> map);

    @POST("tl/statistic/user/expense/luckyGift")
    Observable<HttpResultModel<HttpResultPageModel<TurntableGiftIncomeExpenseDetail>>> getExpenseTurntableGiftListService(@Body Map<String, Object> map);

    @POST("tl/index/feeList")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getFeeTagListService(@Body Map<String, Object> map);

    @POST("tl/shop/component/list")
    Observable<HttpResultModel<List<ComponentsEntity>>> getGameComponentService(@Body Map<String, Object> map);

    @POST("tl/shop/giftBag/get")
    Observable<HttpResultModel<ActivityListEntity>> getGiftBagService(@Body Map<String, Object> map);

    @POST("tl/item/giftBox/list")
    Observable<HttpResultModel<List<GiftBoxEntity>>> getGiftBoxListService(@Body Map<String, Object> map);

    @POST("tl/gift/anchor/giftWall/list")
    Observable<HttpResultModel<List<GiftWallEntity>>> getGiftWallAnchorListService(@Body Map<String, Object> map);

    @POST("tl/gift/giftWall")
    Observable<HttpResultModel<List<GiftWallEntity.GiftWallGiftItemEntity>>> getGiftWallListService(@Body Map<String, Object> map);

    @POST("tl/gift/anchor/giftWall")
    Observable<HttpResultModel<GiftWallEntity>> getGiftWallService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/searchMyGivenPrizeRecordPageByUserId")
    Observable<HttpResultModel<HttpResultPageModel<AwardHistoryEntity>>> getGivenAwardListService(@Body Map<String, Object> map);

    @POST("tl/guard/v1/guard/list")
    Observable<HttpResultModel<List<GuardItemEntity>>> getGuardListService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/statistics/indexRank")
    Observable<HttpResultModel<List<AnchorEntity>>> getHomeCharmTopListService(@Body Map<String, Object> map);

    @POST("tl/index/search/hotKey")
    Observable<HttpResultModel<List<LabelEntity>>> getHotKeyListService(@Body Map<String, Object> map);

    @POST("tl/liveManage/mobile/myLive/liveManager")
    Observable<HttpResultModel<HttpResultPageModel<BannedEntity>>> getHouseListService(@Body Map<String, Object> map);

    @POST("tl/liveManage/mobile/myLive/liveManager/set")
    Observable<HttpResultModel<Object>> getHouseManagerSetService(@Body Map<String, Object> map);

    @POST("tl/user/impression/list")
    Observable<HttpResultModel<List<ImpressionEntity>>> getImpressionListService(@Body Map<String, Object> map);

    @POST("tl/gift/mobile/anchor/income/gift")
    Observable<HttpResultModel<HttpResultPageModel<GiftIncomeExpenseDetail>>> getIncomeGiftListService(@Body Map<String, Object> map);

    @POST("tl/guard/mobile/anchor/income/guard")
    Observable<HttpResultModel<HttpResultPageModel<GuardIncomeExpenseDetail>>> getIncomeGuardListService(@Body Map<String, Object> map);

    @POST("tl/nobility/anchor/income")
    Observable<HttpResultModel<HttpResultPageModel<NobleIncomeExpenseEntity>>> getIncomeNobleListService(@Body Map<String, Object> map);

    @POST("tl/liveHistory/mobile/anchor/income/liveTicket")
    Observable<HttpResultModel<HttpResultPageModel<PaidLiveIncomeExpenseEntity>>> getIncomePaidLiveService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/product/income")
    Observable<HttpResultModel<HttpResultPageModel<HJProductIncomeExpenseEntity>>> getIncomeProductService(@Body Map<String, Object> map);

    @POST("tl/item/mobile/props/incomeRecords")
    Observable<HttpResultModel<HttpResultPageModel<PropsIncomeExpenseDetail>>> getIncomePropsListService(@Body Map<String, Object> map);

    @POST("tl/item/anchor/income/propRecord")
    Observable<HttpResultModel<HttpResultPageModel<PropsIncomeExpenseDetail>>> getIncomePropsPriceListService(@Body Map<String, Object> map);

    @POST("tl/shop/anchor/income/scoreGift")
    Observable<HttpResultModel<HttpResultPageModel<ScoreGiftIncomeExpenseDetail>>> getIncomeScoreGiftListService(@Body Map<String, Object> map);

    @POST(BASE_TL_ANCHOR_INCOME_SERVER_URL)
    Observable<HttpResultModel<IncomeMenuEntity>> getIncomeStatisticsService(@Body Map<String, Object> map);

    @POST("tl/gift/anchor/income/luckyGift")
    Observable<HttpResultModel<HttpResultPageModel<TurntableGiftIncomeExpenseDetail>>> getIncomeTurntableGiftListService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/statistics/getIndexRankConfig")
    Observable<HttpResultModel<List<String>>> getIndexRankConfigService(@Body Map<String, Object> map);

    @POST("tl/statistic/indexRank")
    Observable<HttpResultModel<List<IndexRankEntity>>> getIndexRankService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/invitePk")
    Observable<HttpResultModel<Object>> getInvitePkService(@Body Map<String, Object> map);

    @POST("tl/user/isOpenWeekGuard")
    Observable<HttpResultModel<GuardItemEntity>> getIsOpenWeekGuardService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/lianmai/joinChannel")
    Observable<HttpResultModel<Object>> getJoinLinkMicRoomService(@Body Map<String, Object> map);

    @POST("tl/liveManage/judgeUserBanPost")
    Observable<HttpResultModel<UserEntity>> getJudgeUserBanPostService(@Body Map<String, Object> map);

    @POST("tl/index/mobile/labels")
    Observable<HttpResultModel<List<LabelEntity>>> getLabelListService(@Body Map<String, Object> map);

    @POST("tl/gift/lastStarGiftAnchorList")
    Observable<HttpResultModel<List<WeekStarAnchorEntity>>> getLastStarGiftAnchorListService(@Body Map<String, Object> map);

    @POST("tl/gift/v2/liveCountPage")
    Observable<HttpResultModel<ReceiveGiftRecordPageEntity>> getLiveCountPageService(@Body Map<String, Object> map);

    @POST("tl/liveHistory/liveEvaluation/add")
    Observable<HttpResultModel<Object>> getLiveEndEvaluationService(@Body Map<String, Object> map);

    @POST("tl/exp/room/action/enter")
    Observable<HttpResultModel<Object>> getLiveEnterActionService(@Body Map<String, Object> map);

    @POST("tl/liveHistory/liveEvaluation/get")
    Observable<HttpResultModel<LiveEvaluationEntity>> getLiveEvaluationService(@Body Map<String, Object> map);

    @POST("tl/liveCore/mobile/live/getLiveInitInfo")
    Observable<HttpResultModel<LiveInitInfoEntity>> getLiveInitInfoService(@Body Map<String, Object> map);

    @POST(BASE_TL_EXP_ROOM_ACTION)
    Observable<HttpResultModel<Object>> getLiveLeaveActionService(@Body Map<String, Object> map);

    @POST("tl/index/liveOpenList")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getLiveOpenListService(@Body Map<String, Object> map);

    @GET("tl/index/live/getLivePopularity")
    Observable<HttpResultModel<LiveEntity>> getLivePopularityService(@Query("appId") String str, @Query("userAppId") String str2, @Query("liveId") String str3);

    @POST("tl/live/mobile/live/getHerald")
    Observable<HttpResultModel<LivePreNoticeEntity>> getLivePreNoticeService(@Body Map<String, Object> map);

    @POST("tl/live/liveStatus")
    Observable<HttpResultModel<LiveStatusEntity>> getLiveStatusService(@Body Map<String, Object> map);

    @POST("tl/data/event/config")
    Observable<HttpResultModel<List<LogEventConfigEntity>>> getLogEventConfigService(@Body Map<String, Object> map);

    @POST("tl/turntable/mobile/drawRecord")
    Observable<HttpResultModel<HttpResultPageModel<LotteryRecordEntity>>> getLotteryDrawRecordService(@Body Map<String, Object> map);

    @POST("tl/turntable/lotteryTicket/list")
    Observable<HttpResultModel<HttpResultPageModel<MyTicketEntity>>> getLotteryTicketListService(@Body Map<String, Object> map);

    @POST("tl/turntable/lotteryTicket/num")
    @Deprecated
    Observable<HttpResultModel<LotteryTicketNumEntity>> getLotteryTicketService(@Body Map<String, Object> map);

    @POST("tl/turntable/luckValue")
    Observable<HttpResultModel<LuckValueEntity>> getLuckValueService(@Body Map<String, Object> map);

    @POST("tl/activity/messageBox/pageListWinningLeaveMessage")
    Observable<HttpResultModel<HttpResultPageModel<MessageDetailEntity>>> getMessageDetail(@Body Map<String, Object> map);

    @POST("tl/live/msgBroadcast/send")
    Observable<HttpResultModel<Object>> getMsgBroadcastService(@Body Map<String, Object> map);

    @POST("tl/shop/car/me")
    Observable<HttpResultModel<List<MyCarEntity>>> getMyCarListService(@Body Map<String, Object> map);

    @POST("tl/clan/clanAdmin/anchor/list")
    Observable<HttpResultModel<HttpResultPageModel<MyClanEntity>>> getMyClanListService(@Body Map<String, Object> map);

    @POST("tl/guard/me")
    Observable<HttpResultModel<GuardItemEntity>> getMyGuardInfoService(@Body Map<String, Object> map);

    @POST("tl/live/mobile/myLive/initData")
    Observable<HttpResultModel<MyLiveEntity>> getMyLiveInitDataService(@Body Map<String, Object> map);

    @POST("tl/nobility/myNobility")
    Observable<HttpResultModel<MyNobilityEntity>> getMyNobilityService(@Body Map<String, Object> map);

    @POST("tl/nobility/buy")
    Observable<HttpResultModel<MyAccountEntity>> getNobilityBuyService(@Body Map<String, Object> map);

    @POST("tl/nobility/list")
    Observable<HttpResultModel<List<NobilityEntity>>> getNobilityListService(@Body Map<String, Object> map);

    @POST("tl/nobility/record")
    Observable<HttpResultModel<HttpResultPageModel<NobilityOpenRecordEntity>>> getNobilityOpenRecordListService(@Body Map<String, Object> map);

    @POST("tl/nobility/myRecommendList")
    Observable<HttpResultModel<HttpResultPageModel<NobilityRecommendHistoryEntity>>> getNobilityRecommendListtService(@Body Map<String, Object> map);

    @POST("tl/nobility/source/list")
    Observable<HttpResultModel<List<NobilityDownLoadEntity>>> getNobilitySourceListService(@Body Map<String, Object> map);

    @POST("tl/index/adv/notice")
    Observable<HttpResultModel<BannerEntity>> getNoticeListService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/productItem/list")
    Observable<HttpResultModel<HttpResultPageModel<HJProductContentEntity>>> getOnLineProductListService(@Body Map<String, Object> map);

    @POST("tl/guard/v1/guard/save")
    Observable<HttpResultModel<GuardItemEntity>> getOpenGuardService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/blackList")
    Observable<HttpResultModel<HttpResultPageModel<AnchorEntity>>> getPKBlackListService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/getFP")
    Observable<HttpResultModel<PKRecordEntity>> getPKFPService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/getFP/list")
    Observable<HttpResultModel<List<AnchorEntity>>> getPKRankListService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/getAnchorPkRecordPage")
    Observable<HttpResultModel<HttpResultPageModel<PKRecordEntity>>> getPKRecordListService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/lianmai/matchTimeout")
    Observable<HttpResultModel<Object>> getPKTimeoutService(@Body Map<String, Object> map);

    @POST("tl/liveCore/mobile/live/submit/chargeInfo")
    Observable<HttpResultModel<PayLiveEntity>> getPayLiveSubmitService(@Body Map<String, Object> map);

    @POST("tl/channel/app/live/api")
    Observable<HttpResultModel<Object>> getPhoneCodeService(@Body Map<String, Object> map);

    @POST("tl/store/productItem/getPlayUrl")
    Observable<HttpResultModel<Object>> getPlayUrlService(@Body Map<String, Object> map);

    @POST("tl/shop/myPopularityCard/additionRemainTime")
    Observable<HttpResultModel<PopularCardEntity>> getPopularityCardAdditionRemainTimeService(@Body Map<String, Object> map);

    @POST("tl/shop/myPopularityCard/list")
    Observable<HttpResultModel<List<PopularCardEntity>>> getPopularityCardListService(@Body Map<String, Object> map);

    @POST("tl/live/mobile/live/getPreStartLiveInfo")
    Observable<HttpResultModel<CoverEntity>> getPreStartLiveInfoService(@Body Map<String, Object> map);

    @POST("tl/user/userMark/prefixListForOwn")
    Observable<HttpResultModel<HttpResultPageModel<MedalEntity>>> getPrefixForOwnService(@Body Map<String, Object> map);

    @POST("tl/user/userMark/prefixListForUsed")
    Observable<HttpResultModel<HttpResultPageModel<MedalEntity>>> getPrefixListForUsedService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/productItem/count")
    Observable<HttpResultModel<HJProductEntity>> getProductItemCountService(@Body Map<String, Object> map);

    @POST("tl/store/user/productReport/get")
    Observable<HttpResultModel<ReportTypeEntity>> getProductReportService(@Body Map<String, Object> map);

    @POST("tl/item/fragmentConfig/get")
    Observable<HttpResultModel<PropConfigEntity>> getPropFragmentConfigService(@Body Map<String, Object> map);

    @POST("tl/channel/queryBalance")
    Observable<HttpResultModel<MyAccountEntity>> getQueryBalanceService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/randomMatchPk")
    Observable<HttpResultModel<Object>> getRandomMatchPkService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/lianmai/readyPK")
    Observable<HttpResultModel<Object>> getReadyPKService(@Body Map<String, Object> map);

    @POST("tl/liveHistory/queryRecentlyTicketRoom")
    Observable<HttpResultModel<RelationLastLiveEntity>> getRecentlyTicketRoomService(@Body Map<String, Object> map);

    @POST("tl/user/anchor/commendatory")
    Observable<HttpResultModel<List<AnchorEntity>>> getRecommendAnchorService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/rejectInvitePk")
    Observable<HttpResultModel<Object>> getRejectInvitePkService(@Body Map<String, Object> map);

    @POST("tl/live/startLiveNotify/remainCount")
    Observable<HttpResultModel<StartLiveNotifyEntity>> getRemainCountService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/removeBlack")
    Observable<HttpResultModel<Object>> getRemoveBlackService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/lianmai/rtcDisconnected")
    Observable<HttpResultModel<Object>> getRtcDisconnectedService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/lianmai/rtcError")
    Observable<HttpResultModel<Object>> getRtcErrorService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/getRunAwayTimes")
    Observable<HttpResultModel<PKRecordEntity>> getRunAwayTimesService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/saveGivenPrizeInfo")
    Observable<HttpResultModel<Object>> getSaveGivenPrizeInfoService(@Body Map<String, Object> map);

    @POST("tl/offence/offenceContent/save")
    Observable<HttpResultModel<Object>> getSaveReportService(@Body Map<String, Object> map);

    @POST("tl/auth/userService/mobile/index/syncUserInfo2LiveModuleAndGetToken")
    Observable<HttpResultModel<UserEntity>> getSdkLoginService(@Body Map<String, Object> map);

    @POST("tl/index/search/anchor")
    Observable<HttpResultModel<HttpResultPageModel<AnchorEntity>>> getSearchAnchorListService(@Body Map<String, Object> map);

    @POST("tl/anchorPk/searchInviteAnchorPage")
    Observable<HttpResultModel<HttpResultPageModel<AnchorEntity>>> getSearchInviteAnchorListService(@Body Map<String, Object> map);

    @POST("tl/index/search/live")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getSearchLiveListService(@Body Map<String, Object> map);

    @POST("tl/index/mobile/myLive/searchUsers")
    Observable<HttpResultModel<List<BannedEntity>>> getSearchUserListService(@Body Map<String, Object> map);

    @POST("tl/activity/messageBox/sendOfflinePrivateMessage")
    Observable<HttpResultModel<Object>> getSendOfflinePrivateMessageService(@Body Map<String, Object> map);

    @POST("tl/activity/messageBox/getSendPrizeUnRead")
    Observable<HttpResultModel<MessageDetailEntity>> getSendPrizeUnReadService(@Body Map<String, Object> map);

    @POST("tl/index/live/searchByTag")
    Observable<HttpResultModel<HttpResultPageModel<LiveEntity>>> getSortListService(@Body Map<String, Object> map);

    @POST("tl/gift/starGifList")
    Observable<HttpResultModel<List<GiftDownloadItemEntity>>> getStarGifListService(@Body Map<String, Object> map);

    @POST("tl/gift/starGiftAnchorList")
    Observable<HttpResultModel<List<WeekStarAnchorEntity>>> getStarGiftAnchorListService(@Body Map<String, Object> map);

    @POST("tl/gift/starGiftAnchorList/rank")
    Observable<HttpResultModel<WeekStarAnchorEntity>> getStarGiftAnchorRankService(@Body Map<String, Object> map);

    @POST("tl/gift/starGiftUserList")
    Observable<HttpResultModel<List<WeekStarAnchorEntity>>> getStarGiftUserListService(@Body Map<String, Object> map);

    @POST("tl/gift/starGiftUserList/rank")
    Observable<HttpResultModel<WeekStarAnchorEntity>> getStarGiftUserRankService(@Body Map<String, Object> map);

    @POST("tl/live/getStartLiveAppConfig")
    Observable<HttpResultModel<LiveHelperAppConfigEntity>> getStartLiveAppConfigService(@Body Map<String, Object> map);

    @POST("tl/liveCore/mobile/live/startLive")
    Observable<HttpResultModel<AnchorStartLiveEntity>> getStartLiveService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/statistics/expend")
    Observable<HttpResultModel<List<AnchorEntity>>> getStrengthTopListService(@Body Map<String, Object> map);

    @POST("tl/user/mobile/auth")
    Observable<HttpResultModel<Object>> getSubmitAnchorAuthService(@Body Map<String, Object> map);

    @POST("tl/live/ticketRoom/switch")
    Observable<HttpResultModel<Object>> getSwitchTicketRoomService(@Body Map<String, Object> map);

    @POST("tl/index/tlInitConfig")
    Observable<HttpResultModel<SysParamsInfoEntity>> getSysParamsInfoService(@Body Map<String, Object> map);

    @POST("tl/item/taskBox/list")
    Observable<HttpResultModel<List<TaskBoxEntity>>> getTaskBoxList(@Body Map<String, Object> map);

    @POST("tl/item/taskBox/take")
    Observable<HttpResultModel<Object>> getTaskBoxTake(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/getTaskConfig")
    Observable<HttpResultModel<QMInteractTaskConfigEntity>> getTaskConfigService(@Body Map<String, Object> map);

    @POST("tl/live/ticketRoom/baseInfo")
    Observable<HttpResultModel<TicketRoomInfoEntity>> getTicketRoomBaseInfoService(@Body Map<String, Object> map);

    @POST("tl/live/ticketRoom/userList")
    Observable<HttpResultModel<HttpResultPageModel<UserEntity>>> getTicketRoomUserListService(@Body Map<String, Object> map);

    @POST("tl/nobility/trumpet/detail")
    Observable<HttpResultModel<TrumpetStatusEntity>> getTrumpetStatus(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/luckyGift")
    Observable<HttpResultModel<List<AnchorEntity>>> getTurntableAnchorRankService(@Body Map<String, Object> map);

    @POST("tl/turntable/awardInfo")
    Observable<HttpResultModel<LotteryTurntableInfoEntity>> getTurntableAwardInfoService(@Body Map<String, Object> map);

    @POST("tl/turntable/draw")
    Observable<HttpResultModel<LotteryTurntableDrawEntity>> getTurntableDrawService(@Body Map<String, Object> map);

    @POST("tl/turntable/luckReportHistory/list")
    Observable<HttpResultModel<HttpResultPageModel<LotteryLuckReportEntity>>> getTurntableLuckReportService(@Body Map<String, Object> map);

    @POST("tl/statistic/mobile/drawRank")
    Observable<HttpResultModel<List<LotteryUserRankEntity>>> getTurntableUserRankService(@Body Map<String, Object> map);

    @POST("tl/auth/updateToken")
    Observable<HttpResultModel<UserEntity>> getUpdateTokenService(@Body Map<String, Object> map);

    @POST("tl/user/userService/mobile/index/syncUserInfo2LiveModule")
    Observable<HttpResultModel<Object>> getUpdateUserInfoService(@Body Map<String, Object> map);

    @POST("tl/streams/stream/errorReport")
    Observable<HttpResultModel<Object>> getUploadErrorReportService(@Body Map<String, Object> map);

    @POST("tl/live/mobile/live/liveCoverAuthAdd")
    Observable<HttpResultModel<Object>> getUploadLiveCoverService(@Body Map<String, Object> map);

    @POST("tl/shop/car/use")
    Observable<HttpResultModel<Object>> getUseCarService(@Body Map<String, Object> map);

    @POST("tl/item/propFragment/use")
    Observable<HttpResultModel<Object>> getUsePropService(@Body Map<String, Object> map);

    @POST("tl/user/getUserCardInfo")
    Observable<HttpResultModel<UserEntity>> getUserCardInfo(@Body Map<String, Object> map);

    @POST("tl/user/businessCard/extraInfo")
    Observable<HttpResultModel<UserCardEntity>> getUserCardService(@Body Map<String, Object> map);

    @POST("tl/user/mobile/user/userInfo")
    Observable<HttpResultModel<UserEntity>> getUserInfoService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/getNotice")
    Observable<HttpResultModel<YYNoticeEntity>> getUserLinkNoticeService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/getUserParticipationDrawFlag")
    Observable<HttpResultModel<HdDrawEndEntity>> getUserParticipationDrawFlagService(@Body Map<String, Object> map);

    @POST("tl/item/mobile/userProps/list")
    Observable<HttpResultModel<HttpResultPageModel<BackpackItemEntity>>> getUserPropsListService(@Body Map<String, Object> map);

    @POST("tl/index/liveList")
    Observable<HttpResultModel<HttpResultPageModel<LiveAdEntity>>> getV03AllListService(@Body Map<String, Object> map);

    @POST("tl/index/recommendList")
    Observable<HttpResultModel<HttpResultPageModel<LiveAdEntity>>> getV03RecommendListService(@Body Map<String, Object> map);

    @POST("tl/nobility/vipSeat/list")
    Observable<HttpResultModel<List<UserEntity>>> getVipSeatListService(@Body Map<String, Object> map);

    @POST("tl/live/watchHistory/report")
    Observable<HttpResultModel<Object>> getWatchHistoryService(@Body Map<String, Object> map);

    @POST("tl/liveCore/getWsConnectAddressAndKey")
    Observable<HttpResultModel<WSAddressEntity>> getWebSocketAddressService(@Body Map<String, Object> map);

    @POST("tl/activity/messageBox/getWinningUnRead")
    Observable<HttpResultModel<MessageDetailEntity>> getWinningUnReadService(@Body Map<String, Object> map);

    @POST("tl/gift/v2/giftList")
    Observable<HttpResultModel<List<GiftDownloadItemEntity>>> giftListV2(@Body Map<String, Object> map);

    @POST("tl/store/anchor/isOpenShop")
    Observable<HttpResultModel<HJShopEntity>> isOpenShopService(@Body Map<String, Object> map);

    @POST("tl/activity/bluetooth/modelData")
    Observable<HttpResultModel<LYModelDataEntity>> modelDataListService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/openShop/apply")
    Observable<HttpResultModel<Object>> openShopApplyService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/product/add")
    Observable<HttpResultModel<Object>> productAddService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/product/update/status")
    Observable<HttpResultModel<Object>> productBatchEditService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/product/setting/list")
    Observable<HttpResultModel<HttpResultPageModel<HJProductEntity>>> productConfigListService(@Body Map<String, Object> map);

    @POST("tl/store/user/shelf/detail")
    Observable<HttpResultModel<HJProductEntity>> productDetailWindowService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/product/edit")
    Observable<HttpResultModel<Object>> productEditService(@Body Map<String, Object> map);

    @POST("tl/store/product/info")
    Observable<HttpResultModel<HJProductEntity>> productInfoService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/product/key")
    Observable<HttpResultModel<HJProductKeyEntity>> productKeyService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/productItem/upload")
    Observable<HttpResultModel<HJProductPreUploadEntity>> productPreUploadService(@Body Map<String, Object> map);

    @POST("tl/store/user/product/subscribeInfo/list")
    Observable<HttpResultModel<HttpResultPageModel<HJProductEntity>>> productSubscribeListService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/productItem/complete")
    Observable<HttpResultModel<Object>> productUploadCompleteService(@Body Map<String, Object> map);

    @POST("tl/nobility/recommend")
    Observable<HttpResultModel<Object>> recommend(@Body Map<String, Object> map);

    @POST("tl/store/product/purchased/removeIsUpdateStatus")
    Observable<HttpResultModel<Object>> removeIsUpdateStatusService(@Body Map<String, Object> map);

    @POST("tl/shop/activity/searchActivityListByAppId")
    Observable<HttpResultModel<ArrayList<ActivityListEntity>>> searchActivityListByAppIdService(@Body Map<String, Object> map);

    @POST("tl/live/liveInfoForRecommend")
    Observable<HttpResultModel<AnchorEntity>> searchLive(@Body Map<String, Object> map);

    @POST("tl/nobility/trumpet/send")
    Observable<HttpResultModel<Object>> sendTrumpet(@Body Map<String, Object> map);

    @POST("tl/nobility/rank/hidden")
    Observable<HttpResultModel<Object>> setHiddenInRankList(@Body Map<String, Object> map);

    @POST("tl/store/anchor/shopApplyRecord/get")
    Observable<HttpResultModel<HJShopEntity>> shopApplyRecordService(@Body Map<String, Object> map);

    @POST("tl/store/anchor/shopAuthInfo")
    Observable<HttpResultModel<HJShopEntity>> shopAuthInfoService(@Body Map<String, Object> map);

    @POST("tl/live/startLiveNotify/clickCountUpdate")
    Observable<HttpResultModel<Object>> startLiveNoticeClickCountUpdateService(@Body Map<String, Object> map);

    @POST("tl/statistic/buriendPoints/event")
    Observable<ResponseBody> statisticsReportService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/status/update")
    Observable<HttpResultModel<Object>> statusUpdateService(@Body Map<String, Object> map);

    @POST("tl/activity/liveDraw/submitAppeal")
    Observable<HttpResultModel<Object>> submitAppealService(@Body Map<String, Object> map);

    @POST("tl/store/user/productReport/submit")
    Observable<HttpResultModel<Object>> submitProductReportService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/taskConfigAdd")
    Observable<HttpResultModel<QMInteractTaskEntity>> taskConfigAddService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/anchor/taskConfigDel")
    Observable<HttpResultModel<Object>> taskConfigDelService(@Body Map<String, Object> map);

    @POST("tl/gift/thermometer/bomb")
    Observable<HttpResultModel<Object>> thermometerBombService(@Body Map<String, Object> map);

    @POST("tl/nobility/trumpet/clickCountUpdate")
    Observable<HttpResultModel<Object>> updateClickTrumpetCount(@Body Map<String, Object> map);

    @POST("tl/user/impression/update")
    Observable<HttpResultModel<List<ImpressionEntity>>> updateImpressionListService(@Body Map<String, Object> map);

    @Headers({"urlName:upload"})
    @POST
    Observable<HttpResultModel<UploadFileEntity>> uploadFile(@Url String str, @Body RequestBody requestBody);

    @POST("data/action/log")
    Observable<HttpResultModel<Object>> uploadLogEventService(@Body Map<String, Object> map);

    @POST("tl/shop/myPopularityCard/use")
    Observable<HttpResultModel<Object>> usePopularityCardService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/user/commit")
    Observable<HttpResultModel<Object>> userCommitTaskListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/user/userPendingTask")
    Observable<HttpResultModel<QMInteractTaskEntity>> userPendingTaskService(@Body Map<String, Object> map);

    @POST("tl/store/user/product/buy")
    Observable<HttpResultModel<MyAccountEntity>> userProductBuyService(@Body Map<String, Object> map);

    @POST("tl/store/productItem/list")
    Observable<HttpResultModel<HttpResultPageModel<HJProductDetailEntity>>> userProductDetailListService(@Body Map<String, Object> map);

    @POST("tl/store/user/product/list")
    Observable<HttpResultModel<List<HJProductEntity>>> userProductListService(@Body Map<String, Object> map);

    @POST("tl/store/product/purchased/list")
    Observable<HttpResultModel<HttpResultPageModel<HJProductEntity>>> userProductPurchasedListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/user/record")
    Observable<HttpResultModel<List<QMInteractTaskEntity>>> userRecordListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/user/showTask")
    Observable<HttpResultModel<QMInteractTaskListEntity>> userShowTaskListService(@Body Map<String, Object> map);

    @POST("tl/activity/intimate/user/taskList")
    Observable<HttpResultModel<QMInteractTaskListEntity>> userTaskListService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/anchor/setQuiet")
    Observable<HttpResultModel<Object>> videoRoomAnchorSetQuietService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/anchor/call/detail")
    Observable<HttpResultModel<YYLinkApplyEntity>> videoRoomLinkDetailService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/user/invite/deal")
    Observable<HttpResultModel<YYRTCEntity>> videoRoomUserAcceptService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/user/apply/call")
    Observable<HttpResultModel<Object>> videoRoomUserApplyLinkService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/user/apply/list")
    Observable<HttpResultModel<List<YYLinkApplyEntity>>> videoRoomUserApplyListService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/user/connectSuccess")
    Observable<HttpResultModel<Object>> videoRoomUserConnectSuccessService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/user/disconnect")
    Observable<HttpResultModel<Object>> videoRoomUserDisconnectLinkService(@Body Map<String, Object> map);

    @POST("tl/audio/videoRoom/user/rtcError")
    Observable<HttpResultModel<Object>> videoRoomUserRTCErrorService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/getUserSurplusLikeNum")
    Observable<HttpResultModel<YYLinkApplyEntity>> voiceRoomGetLikeNumService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/likeSeat")
    Observable<HttpResultModel<Object>> voiceRoomLikeActionService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/anchor/muteSeat")
    Observable<HttpResultModel<Object>> voiceRoomMuteActionService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/seat/list")
    Observable<HttpResultModel<List<YYLinkApplyEntity>>> voiceRoomSeatListService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/accept")
    Observable<HttpResultModel<YYRTCEntity>> voiceRoomUserAcceptService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/apply/list")
    Observable<HttpResultModel<List<YYLinkApplyEntity>>> voiceRoomUserApplyListService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/apply/call")
    Observable<HttpResultModel<Object>> voiceRoomUserApplyService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/disconnect")
    Observable<HttpResultModel<Object>> voiceRoomUserDisconnectLinkService(@Body Map<String, Object> map);

    @POST("tl/audio/voiceRoom/user/refuse")
    Observable<HttpResultModel<YYRTCEntity>> voiceRoomUserRefuseService(@Body Map<String, Object> map);
















































    @POST("tl/anchorPk/videoCall/call/uri")
    Observable<HttpResultModel<MLCallUrlEntity>> getMLCallUrlService(@Body Map<String, Object> map);
    @POST("tl/anchorPk/videoCall/homeLabel")
    Observable<HttpResultModel<List<LabelEntity>>> getMLHomeLabelService(@Body Map<String, Object> map);
}
