/*
 * =============================================
 * @author tangxuecheng
 * @description 获取服务器返回码对应的提示
 * @date (2017/9/6 - 9:56)
 * =============================================
 */


let resTips = [
    {code: 0, define: " NO_ERROR", msg: "请求响应勿错误"},
    {code: 1, define: " INTERNAL_ERROR", msg: "系统内部错误"},
    {code: 2, define: " PARAM_ERROR", msg: " 参数错误"},
    {code: 3, define: " API_UNSUPPORTED ", msg: "不支持的API"},
    {code: 4, define: "ABOLITION_API", msg: "废除的API"},
    {code: 5, define: "VERIFICATION_FAILURE", msg: "验证失效"},
    {code: 6, define: "NO_CONTENT ", msg: " 无内容"},
    {code: 7, define: "ANTI_CHEATING", msg: "防刷策略，IP被封"},
    {code: 8, define: "LOWER_SDK_VERSION", msg: "SDK版本过低"},
    {code: 50, define: "MISSING_SVR ", msg: "SDK版本号缺失"},
    {code: 51, define: "MISSING_DEVICE", msg: "  设备名缺失"},
    {code: 52, define: "MISSING_CUID", msg: "用户ID缺失"},
    {code: 53, define: "MISSING_CLIENT_ID", msg: "客户端ID缺失"},
    {code: 54, define: "MISSING_DEVICE_ID", msg: "设备ID缺失"},
    {code: 100000, define: "ERROR_FORMAT_REQUEST", msg: "请求数据格式错误"},
    {code: 102000, define: "MISSING_API_VERSION", msg: "使用API版本信息缺失"},
    {code: 102010, define: "ERROR_API_FORMAT", msg: "API格式不正确"},
    {code: 103000, define: "MISSING_APP_INFO", msg: "应用信息缺失 "},
    {code: 103010, define: "MISSING_APP_ID", msg: "应用ID 信息缺失 "},
    {code: 103011, define: "ERROR_APP_ID", msg: "应用ID 信息错误，MSSP 未收录 "},
    {code: 103012, define: "DISABLED_APP_ID", msg: "应用ID 无效，MSSP 上未生效 "},
    {code: 103020, define: "ERROR_CHANNEL_ID", msg: "渠道ID 信息错误 "},
    {code: 103030, define: "MISSING_APP_VERSION", msg: "应用版本信息缺失 "},
    {code: 104000, define: "MISSING_DEVICE_INFO", msg: "设备信息缺失 "},
    {code: 104010, define: "MISSING_DEVICE_TYPE", msg: "设备类型信息缺失 "},
    {code: 104011, define: "ERROR_DEVICE_TYPE", msg: "设备类型信息错误 "},
    {code: 104020, define: "MISSING_OS_TYPE", msg: "操作系统信息缺失 "},
    {code: 104021, define: "ERROR_OS_TYPE", msg: "操作系统信息错误 "},
    {code: 104030, define: "MISSING_OS_VERSION", msg: "操作系统版本信息缺失 "},
    {code: 104031, define: "ERROR_OS_VERSION", msg: "操作系统版本信息错误"},
    {code: 104050, define: "MISSING_VENDOR", msg: "厂商信息缺失 "},
    {code: 104060, define: "MISSING_MODEL", msg: "设备型号信息缺失 "},
    {code: 104070, define: "MISSING_ANDROID_ID", msg: "android id 缺失 "},
    {code: 104071, define: "ERROR_FORMAT_ANDROID_ID", msg: "android id 格式错误 "},
    {code: 104080, define: "MISSING_IMEI", msg: "imei号缺失"},
    {code: 104081, define: "MISSING_IMEI_MD5", msg: "imei_md5缺失"},
    {code: 104090, define: "MISSING_MAC", msg: "mac信息缺失 "},
    {code: 104091, define: "ERROR_MAC", msg: "mac信息格式错误"},
    {code: 104100, define: "MISSING_SCREEN_SIZE_WIDTH", msg: "设备屏幕尺寸宽度缺失 "},
    {code: 104110, define: "MISSING_SCREEN_SIZE_HEIGHT", msg: "设备屏幕尺寸高度缺失 "},
    {code: 105000, define: "MISSING_NETWORK_INFO", msg: "网络环境信息缺失 "},
    {code: 105010, define: "MISSING_IPV4", msg: "网络地址信息缺失 "},
    {code: 105011, define: "ERROR_FORMAT_IPV4", msg: "网络地址信息格式错误 "},
    {code: 105020, define: "MISSING_CONNECTION_TYPE", msg: "网络连接类型缺失 "},
    {code: 105021, define: "ERROR_CONNECTION_TYPE", msg: "网络连接类型错误 "},
    {code: 105030, define: "MISSING_OPERATOR_TYPE", msg: "网络运营商类型缺失 "},
    {code: 105031, define: "ERROR_OPERATOR_TYPE", msg: "网络运营商类型错误 "},
    {code: 106000, define: "MISSING_COORDINATE_TYPE", msg: "坐标类型信息缺失 "},
    {code: 106001, define: "ERROR_COORDINATE_TYPE", msg: "坐标类型信息错误 "},
    {code: 106010, define: "MISSING_LONGITUDE", msg: "经度信息缺失 "},
    {code: 106020, define: "MISSING_LATITUDE", msg: "纬度信息缺失 "},
    {code: 106030, define: "MISSING_GPS_TIMESTAMP", msg: "定位时间戳信息缺失 "},
    {code: 107000, define: "MISSING_ADSLOT", msg: "广告位信息缺失"},
    {code: 107010, define: "MISSING_ADSLOT_ID", msg: "广告位ID 缺失"},
    {code: 107011, define: "ERROR_ADSLOT_ID", msg: "广告位ID 未收录"},
    {code: 107012, define: "DISABLED_ADSLOT_ID", msg: "广告位ID 未启用"},
    {code: 107013, define: "NOT_MATCH_ADSLOT_ID", msg: "广告位ID 与应用ID 不匹配"},
    {code: 107020, define: "MISSING_ADSLOT_SIZE_WIDTH", msg: "广告位尺寸宽度缺失 "},
    {code: 107030, define: "MISSING_ADSLOT_SIZE_HEIGHT", msg: "广告位尺寸高度缺失 "},
]

module.exports = function (code) {
    for (let i = 0; i < resTips.length; i++) {
        if (code == resTips[i].code) {
            return resTips[i];
        }
    }
    return false;
}