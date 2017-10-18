/*
 * =============================================
 * @author tangxc
 * @description 封装一些常用方法
 * @Date (2016/7/29 - 9:33)
 * =============================================
 */

import Md5 from './md5';


/**
 * 获取url参数值
 * @method getQueryString
 * @param {String} name url属性名
 * @return {String} 返回属性值
 * @Date 2016/7/28 - 18:12
 */
"use strict";
export let getQueryString = (name) => {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
/**
 * 判断是否是移动端访问
 * @method isMobile
 * @param {String} 参数名 参数说明
 * @return {String} 返回值说明
 * @Date 2016/7/28 - 18:20
 */
export let isMobile = () => {
    let u = navigator.userAgent,
        a = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"],
        res = false;
    for (let i = 0; i < a.length; i++) {
        if (u.indexOf(a[i]) > 0) {
            res = true;
            break;
        }
    }
    return res;
}
/**
 * 方法说明
 * @method 检测对象是否为空
 * @param {object} a 需要检测的对象
 * @return {boolean} 返回false 为空
 * @Date 2016/7/29 - 9:04
 */
export let isNotBlank = (a) => {
    if (a == undefined || a == null || a == "null" || a == "undefined" || a.toString().trim().length <= 0) {
        return false
    }
    return true
}
/**
 * 根据相应设备 返回对应的点击事件
 * @method tap
 * @param { }
 * @return {String} 返回对应的事件名
 * @Date 2016/7/29 - 9:06
 */
export let tap = () => {
    return isMobile() ? "touchend" : "click";
}

/**
 * 添加修改url的属性或属性值
 * @method changeURLArg
 * @param1 {String} url url
 * @param2 {String} arg 属性名
 * @param3 {String} arg_val 属性值
 * @return {String} 返回当前修改后的url
 * @Date 2016/7/29 - 9:08
 */
export let changeURLArg = (url, arg, arg_val) => {
    let pattern = arg + '=([^&]*)';
    let replaceText = arg + '=' + arg_val;
    if (url.match(pattern)) {
        let tmp = '/(' + arg + '=)([^&]*)/gi';
        tmp = url.replace(eval(tmp), replaceText);
        return tmp;
    } else {
        if (url.match('[\?]')) {
            return url + '&' + replaceText;
        } else {
            return url + '?' + replaceText;
        }
    }
    return url + '\n' + arg + '\n' + arg_val;
}

/**
 * 删除url参数值
 * @method delQueStr
 * @param {String} url 需要删除的url
 * @param {String} ref 需要删除的值
 * @return {String} 返回删除后的url
 * @Date 2016/8/29 - 9:16
 */
export let delQueStr = (url, ref) => {
    let str = "";
    if (url.indexOf('?') != -1)
        str = url.substr(url.indexOf('?') + 1);
    else
        return url;
    let arr = "";
    let returnurl = "";
    if (str.indexOf('&') != -1) {
        arr = str.split('&');
        for (let i in arr) {
            if (arr[i].split('=')[0] != ref) {
                returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
            }
        }
        return url.substr(0, url.indexOf('?')) + "?" + returnurl.substr(0, returnurl.length - 1);
    }
    else {
        arr = str.split('=');
        if (arr[0] == ref)
            return url.substr(0, url.indexOf('?'));
        else
            return url;
    }
}


/**
 * 将url属性生成为对象
 * @method getRequestParameter
 * @return {object} 返回对应属性值
 * @Date 2016/7/29 - 9:12
 */
export let getRequestParameter = () => {
    let url = location.search;
    let theRequest = new Object();
    if (url.indexOf("?") != -1) {
        let str = url.substr(1);
        let newStrs = str.split("?").join('&');
        let strs = newStrs.split("&");
        for (let i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
/**
 * 封装操作本地存储的方法
 * @object LocalStorage
 * @Date 2016/7/29 - 9:14
 */
export let LocalStorage = {
    isLocalStorage: window.localStorage ? true : false,
    set: function (key, value) {
        if (this.isLocalStorage) {
            window.localStorage.setItem(key, value);
        }
    },
    get: function (key) {
        if (this.isLocalStorage) {
            return window.localStorage.getItem(key) ? window.localStorage.getItem(key) : null;
        }
    },
    remove: function (key) {
        if (this.isLocalStorage) {
            localStorage.removeItem(key);
        }
    }
}

/**
 * 获取ookie
 * @method getCookie
 * @param {String} name 参数说明
 * @Date 2016/8/29 - 9:17
 */

export let getCookie = (name) => {
    let arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
/**
 * 删除cookie
 * @method setCookie
 * @param {String} name 设置cookie
 * @param {String} value 设置cookie值
 * @Date 2016/8/29 - 9:18
 */

export let setCookie = (name, value)=> {
    let Days = 30;
    let exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + "; path=/;domain=" + document.domain;
    ;
}

/**
 * 删除cookie
 * @method deleteCookie
 * @param {String} name 要删除的名字
 * @Date 2016/8/29 - 9:19
 */
export let deleteCookie = (name) => {
    let date = new Date();
    date.setTime(date.getTime() - 10000);
    document.cookie = name + "=; expire=" + date.toGMTString() + ";path=/;";
};


/**
 * 创建imei
 * @method createImei
 * @return {String} 返回imei
 * @author Tangxc
 * @Date 2017/8/29 - 17:21
 */
export let createImei = ()=> {
    let chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
    let imei = "";
    for (let i = 0; i < 15; i++) {
        let id = Math.floor(Math.random() * 10);
        imei += chars[id];
    }
    if (imei.indexOf('0') === 0) {
        imei = imei.split('');
        imei.splice(0, 1, Math.floor(Math.random() * 8 + 1));
        imei = imei.join('');
    }
    return imei;
}

/**
 * 生成随机ID
 * @method createRandomId
 * @param {Number} n 生成id长度
 * @return {String} 返回id值
 * @author Tangxc
 * @Date 2017/8/30 - 16:16
 */

export let createRandomId = (n)=> {
    let chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
    let rId = "";
    for (let i = 0; i < n; i++) {
        let id = Math.floor(Math.random() * 36);
        rId += chars[id];
    }
    if (rId.indexOf('0') === 0) {
        rId = rId.split('');
        rId.splice(0, 1, Math.floor(Math.random() * 8 + 1));
        rId = rId.join('');
    }
    return rId;
}

/**
 * 发送监播请求
 * @method sendRequest
 * @param {String} src 请求的url
 * @author Tangxc
 * @Date 2017/9/6 - 11:32
 */
export let sendRequest = (src)=> {
    var oImg = document.createElement("img");
    oImg.src = src;
    oImg.style.display = 'none';
    document.body.appendChild(oImg);
    document.body.removeChild(oImg);
}

/**
 * 校验图片是否完整
 * @method checkImgMd5
 * @param {String} adData 广告数据
 * @param {String} imgMd5 图片md5值
 * @return {boolean}
 * @author Tangxc
 * @Date 2017/9/6 - 15:32
 */

export let checkImgMd5 = (adData)=> {
    return true;
    if (adData.nativ.imgurl.length) {
        let imgUrl = adData.nativ.imgurl;
        let imgMd5 = adData.nativ.imgmd5;
        for (let i = 0; i < imgUrl.length; i++) {
            if (Md5(imgUrl[i]) != imgMd5[i]) {
                console.info('广告图片校验失败');
                return false;
            }
        }
        return true;
    } else {
        console.info('广告图片校验失败');
        return false;
    }
}

/**
 * 将json格式数据转换成url地址
 * @method urlEncode
 * @param {String} param 要转码的json对象
 * @param {String} key 添加参数
 * @param {boolean} encode 是否编码
 * @return {String} 返回转码成功的url
 * @author Tangxc
 * @Date 2017/9/7 - 14:19
 */


export let urlEncode = (param, key, encode) => {
    if (param == null) return '';
    var paramStr = '';
    var t = typeof (param);
    if (t == 'string' || t == 'number' || t == 'boolean') {
        paramStr += '&' + key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param);
    } else {
        for (var i in param) {
            var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
            paramStr += urlEncode(param[i], k, encode);
        }
    }
    return paramStr;
}


/**
 * 发送jsonp请求
 * @method JSONP
 * @param {String} url 请求url
 * @param {Object} data 请求数据
 * @param {String} callbackName 请求回调方法
 * @author Tangxc
 * @Date 2017/9/7 - 14:17
 */
export let JSONP = (url, data, callbackName) => {
    let script = document.createElement('script');
    let callback = callbackName ? callbackName : 'getAdCallback';
    let reqUrl = url + '?callback=' + callback + urlEncode(data)
    script.setAttribute('src', reqUrl);
    document.getElementsByTagName('head')[0].appendChild(script);
}

/**
 * 插入html字符串
 * @method append
 * @param {Object} parent 要插入的父元素
 * @param {String} text 要插入的文本字符串
 * @author Tangxc
 * @Date 2017/9/7 - 14:25
 */
export let append = (parent, text) => {
    if (typeof text === 'string') {
        var temp = document.createElement('div');
        temp.innerHTML = text;
        // 防止元素太多 进行提速
        var frag = document.createDocumentFragment();
        while (temp.firstChild) {
            frag.appendChild(temp.firstChild);
        }
        parent.appendChild(frag);
    }
    else {
        parent.appendChild(text);
    }
}
/**
 * 绑定事件
 * @method addEvent
 * @param {Object} target 要绑定的对象
 * @param {String} type 要绑定的事件
 * @param {Funtion} fn 绑定的执行回调函数
 * @author Tangxc
 * @Date 2017/9/7 - 14:28
 */
export let addEvent = (target, type, fn) => {
    if (target.addEventListener) {
        target.addEventListener(type, fn);
    } else {
        target.attachEvent("on" + type, fn);
    }
}


/**
 * 查询父级对象
 * @method closest
 * @param {Object} el 当前对象
 * @param {String} selector 要查询的父级选择器
 * @author Tangxc
 * @Date 2017/9/7 - 14:56
 */
export let closest = (el, selector) => {
    var matchesSelector = el.matches || el.webkitMatchesSelector || el.mozMatchesSelector || el.msMatchesSelector;

    while (el) {
        if (matchesSelector.call(el, selector)) {
            break;
        }
        el = el.parentElement;
    }
    return el;
}