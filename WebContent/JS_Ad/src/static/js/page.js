
/**
 * 拼装广告HTML
 * @method page
 * @param {object} adData 广告参数
 * @return {String} 拼装好的广告html
 * @author Tangxc
 * @Date 2017/9/4 - 15:47
 */

import GroupAd from '../ad_page/group';
import MinAd from '../ad_page/min';
import MaxAd from '../ad_page/max';


let pageHtml = ``;
module.exports  = function (adData) {

    let ad_type = adData.ad_type; // 广告类型。1:横幅，2: 开屏，3: 插屏，4：原生
    let creative_type = adData.creative_type; // 0：无创意类型，1：纯文字创意，2：纯图片创意， 3:图文混合创意，4：H5创意。5:信息流广告


    if(creative_type != 5){ // 如果不是信息流广告 返回空
        return '';
    }

    let nativ = adData.nativ;

    let adCont = ``;

    if(nativ.type == 1){ //组图
        adCont  = GroupAd(adData);
    }

    if(nativ.type == 2){ //小图
        adCont = MinAd(adData);
    }

    if(nativ.type == 3){ //大图 
        adCont = MaxAd(adData);

    }
    let clktrackers = adData.clktrackers;

    pageHtml = `<div class="m-spread" clktrackers="${clktrackers}" >` +adCont +`</div>`;
    return pageHtml;
}