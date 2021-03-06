/*!
 * =============================================
 * @author Tangxc
 * @description 请求广告数据
 * @date (2017/9/6 - 9:58)
 * =============================================
 */

import Md5 from './md5';
import * as Util from './util.js';
import ModelData from './model_data';


//随机获取Imei
let imei = '';
let mac = '';
let adImei = Util.LocalStorage.get('ad_imei');
let adMac = Util.LocalStorage.get('ad_mac');

if (adImei) {
    imei = adImei;
} else {
    imei = Md5(Util.createImei());
    Util.LocalStorage.set('ad_imei', imei);
}
//mac
if (adMac) {
    mac = adMac;
} else {
    mac = Util.createRandomId(2) + ':' + Util.createRandomId(2) + ':' + Util.createRandomId(2) + ':' + Util.createRandomId(2) + ':' + Util.createRandomId(2) + ':' + Util.createRandomId(2);
    Util.LocalStorage.set('ad_mac', mac);
}

//随机获取手机型号
let modelData = ModelData[Math.floor(Math.random() * ModelData.length)].toUpperCase().split('_')[1];

//android_id
let android_id = Util.createRandomId(16);



let reqjson = {
    "api_version": "1.1.0",
    "app": {
        "app_id": "2659",
        "channel_id": "3",
        "app_version": "1.7.6.a",
        // "package_name": "com.ssp_sdk.demo"
    },
    "device": { 
        "device_type": 4,
        "os_type": "Android",
        "os_version": "7.0",
        "vendor": "GIONEE",
        "model": modelData,
        "android_id": android_id,
        "imei_md5": imei,
        "mac": mac.toLowerCase(),
        "w": document.documentElement.clientWidth,
        "h": document.documentElement.clientHeight,
        "dpi": 480,
        "ua": window.navigator.appVersion,
        // "web_ua": window.navigator.userAgent,
        // "por": 0,
        // "language": "zh",
        // "rp": "1080_1920",
        // "isroot": 1,
        // "btmac": "02:00:00:00:00:00",
        // "pdunid": "4HCQNNAYTKNVIRSC",
        // "cputy": "unknown",
        // "cpusn": "0000000000000000",
        // "imsi": "",
        "app_list": ""
    },
    "adslot": {
        // "adslot_id": "3551",
        "adslot_id": "4043",
        // "adslot_id": "3977",
        "adslot_w": document.documentElement.clientWidth,
        "adslot_h": 0
    },
    "network": {
        "ip": "192.168.122.2",
        "connect_type": 0,
        "carrier": 0,
        "cellular_id": "23257355",
        "lac": "42289",
        "mcc": "460",
        "bss_id": "70:f9:6d:b0:02:71",
        /* "netwk_id": "0",
         "ssid": "\"gionee_staff\"",
         "lksd": 65,
         "rssi": -68,
         "roaming": 0,
         "stbif": []*/
    },
    "gps": {
        "coordinate_type": 1,
        "lon": -1,
        "lat": -1,
        "timestamp": new Date().getTime()
    }
}


module.exports = {
    "channel_id": "6",
    "device": modelData,
    "cuid": imei,
    "client_id": "Ph5xKWbAgaZBM4jdAINkJw==",
    "device_id": "t+pCPWA6SxGNnDuJ0V55x4VUMw2dPJHimyovT/58orQ=",
    "os_level": "24",
    "sn": "SSP_SDK",
    "svr": "1.7.7",
    "reqjson": JSON.stringify(reqjson)
}
