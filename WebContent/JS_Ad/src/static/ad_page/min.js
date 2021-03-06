/*
 * =============================================
 * @author Tangxc
 * @description 小图广告html拼装
 * @date (2017/9/7 - 15:36)
 * =============================================
 */

import * as Util from '../js/util.js';

module.exports = function (adData) {

    let nativ = adData.nativ;
    let interaction_type = adData.interaction_type;
    let clkurl = adData.clkurl.trim();
    let imgUrl = adData.nativ.imgurl;
    let downloadBtn = ``;
    let isAPK = (clkurl.substr(clkurl.length-3,4).toUpperCase()) == 'APK' ? true : false;
    
    let checkImgMd5 = Util.checkImgMd5(adData);

    if (!checkImgMd5) {
        return '';
    }

    if (isAPK) {
        downloadBtn = `<a href="${clkurl}" class="download-btn">立即下载</a>`;
        clkurl = 'javascript:;'
    }else if(interaction_type == 0){
        clkurl = 'javascript:;'
    }

    let html = `
        <div class="min">
            <div class="info ">
                <p class="u-title">
                    <a href="${clkurl}" class="j-click"  title="${nativ.title}">
                        <span>${nativ.title}</span>
                    </a>
                </p>
                <p class="sub-title">
                    <a href="${clkurl}" class="j-click"  title="${nativ.title}">
                        <span>${nativ.sub_title}</span>
                    </a>
                </p>
            </div>
            
            <a href="${clkurl}"  class="img j-click"  title="${nativ.title}">
                <em class="icon">广告</em>
                <img src="${imgUrl[0]}" alt="${nativ.title}">
            </a>
        </div>
        ${downloadBtn}
    `;

    return html;
}