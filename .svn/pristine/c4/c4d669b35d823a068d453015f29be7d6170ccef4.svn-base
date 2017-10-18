import ReqData from './static/js/req_data';
import ResTipsFn from './static/js/response_tips';
import getPage from './static/js/page';
import * as Util from './static/js/util.js';

//导入css
require('./static/css/main.css');

//广告请求URL
// const GET_AD_TEST_URL = 'http://sspzx.ssptest.gionee.com/v1.1/getad/';
const GET_AD_TEST_URL = 'https://sspzx.gionee.com/v1.1/getad/';



//advertising
//广告请求成功
window.onload = function () {
    function adGetSuccessFn(ResData) {
        let adResTips = ResTipsFn(ResData.error_code);
        if (ResData.error_code == 0) {
            let adms = ResData.adms; //返回 ad array 多个广告请求
            for (let i = 0; i < adms.length; i++) {
                let adItem = adms[i];
                let imptrackers = adItem.imptrackers; // 曝光追踪地址，允许有多个追踪地址
                for (let i = 0; i < imptrackers.length; i++) {//展示曝光
                    let impUrlItem = imptrackers[i];
                    Util.sendRequest(impUrlItem);
                }
                Util.append( document.body,getPage(adItem));
            }
        } else {
            console.log(adResTips.msg) 
        }
        
        let jClick = document.getElementsByClassName('j-click');

        //点击发送监播请求
        for(let i = 0; i< jClick.length;i++){
            jClick[i].onclick = function () {
                let spreadElement =  Util.closest(this,'.m-spread');
                let clktrackers = spreadElement.getAttribute('clktrackers').split(',');
                for (let i = 0; i < clktrackers.length; i++) {
                    Util.sendRequest(clktrackers[i]);
                }
            }
        }
    }

    //广告请求回调
    window.getAdCallback = function (res) {
        console.log(res);
        console.log('广告请求成功');
        adGetSuccessFn(res);
    }

    //发送jsonp请求广告服务器 
    Util.JSONP(GET_AD_TEST_URL,ReqData);
}















