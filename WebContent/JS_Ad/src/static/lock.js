document.addEventListener("DOMContentLoaded", function () {
    String.prototype.replaceByMyTag = function (arr1, arr2) {
        var str = this;
        var sarr = this.substring(this.indexOf("?") + 1).split("&");
        var arr = [];
        for (var i = 0; i < sarr.length; i++) {
            arr.push(sarr[i].split("=")[1]);
        }
        ;
        for (var i = 0; i < arr2.length; i++) {
            for (var j = 0; j < arr.length; j++) {
                if (arr[j] == arr1[i]) {
                    str = str.replace(arr1[i], arr2[i] != undefined ? arr2[i] : arr1[i]);
                }
                ;
            }
            ;
        }
        ;
        return str;
    };

    var arr3 = ["__DEVICE_NAME__", "__APP_VERSION__", "__TIMESTAMP__", "__OS__", "__IMEI__", "__MAC__", "__MAC1__", "__ANDROIDID__", "__ANDROIDID1__", "__AAID__", "__IDFA__", "__OPENUDID__", "__DUID__", "__IP__", "__APP__", "[pyid]"];
    var arr4 = [result("d"), result("v"), time(), result("o"), result("IMEI") ? (result("IMEI").toLowerCase()) : result("IMEI"), result("m"), result("m1"), result("a"), result("a1"), result("aa"), result("idfa"), result("oi"), result("dui"), result("ip"), result("app"), result("IMEI") ? (result("IMEI").toLowerCase()) : result("IMEI")];

    function result(arg) {
        return getQueryString(arg) || getQueryString1(arg);
    };
    function time() {
        return new Date().getTime();
    };
    ;
    var oImg = document.getElementsByTagName("img");
    var len = oImg.length;
    if (len > 0) {
        for (var i = 0; i < len; i++) {
            if (oImg[i].getAttribute("height") || oImg[i].getAttribute("width")) {
                oImg[i].removeAttribute("height");
                oImg[i].removeAttribute("width");
                oImg[i].style.maxWidth = "100%";
            }
            if (oImg[i].style.height || oImg[i].style.width) {
                oImg[i].style.height = "auto";
                oImg[i].style.width = "auto";
                oImg[i].style.maxWidth = "100%";
            }
        }
        ;
        Array.prototype.forEach.call(oImg, function (item) {
            item.onload = function () {
            };
            item.onerror = function () {
                item.src = "/src/static/img/error.png";
            };
        });
    }
    ;
    var tagArr = ["p", "div", "section"];
    tagArr.forEach(function (item) {
        alignTag(item);
    });
    function alignTag(arg) {
        var oParent = document.getElementsByTagName(arg);
        Array.prototype.forEach.call(oParent, function (item) {
            var aImg = item.getElementsByTagName("img");
            if (aImg.length) {
                try {
                    item.classList.add("inherit");
                } catch (ex) {
                    console.log("添加继承样式失败");
                }
            }
            ;
        });
    };
    var oBtn = document.querySelectorAll(".reader");
    if (oBtn.length) {
        Array.prototype.forEach.call(oBtn, function (item) {
            item.onclick = function (e) {
                e.preventDefault();
                check(item, "9");
                return false;
            };
        });
    }
    ;
    var oYueDu = document.querySelector(".yuedu");
    oYueDu.onclick = function (e) {
        e.preventDefault();
        check(oYueDu, "10");
        return false;
    };
    var oDownload = document.querySelector(".downloada");
    if (oDownload) {
        oDownload.onclick = function () {
            check(oDownload, "11");
        };
    }
    ;
    function check(obj, num) {
        var frm = document.forms["gionee_form"];
        frm.innerHTML = "";
        var ifm = document.createElement("iframe");
        ifm.name = "gionee_iframe";
        ifm.id = "gionee_iframe";
        ifm.width = "1";
        ifm.height = "1";
        ifm.style.display = "none";
        var ifm2 = document.getElementById("gionee_iframe");
        if (!ifm2) {
            document.body.appendChild(ifm);
        }
        ;
        var action1 = "http://ads.gionee.com/ads/click.do";
        frm.innerHTML = "<input type='hidden' name='as' value='1' /><input type='hidden' name='mt' value='2' /><input type='hidden' name='cid' value='' /><input type='hidden' name='crid' value='' /><input type='hidden' name='OS' value='0' /><input type='hidden' name='DUID' value='' /><input type='hidden' name='ts' value='' />";
        frm.cid.value = num;
        frm.crid.value = getPageId();
        frm.DUID.value = getQueryString("u") || getQueryString1("u");
        frm.ts.value = new Date().getTime();
        var AAID = obj.getAttribute("uuid");
        var d = getQueryString("d") || getQueryString1("d") || getName(navigator.userAgent);
        var v = getQueryString("v") || getQueryString1("v");
        var IMEI = getQueryString("IMEI") || getQueryString1("IMEI");
        if (num == "1008") {
            addItem(frm, "OpenUDID", obj.getAttribute("href"));
            addItem(frm, "IDFA", obj.getAttribute("title"));
        }
        ;
        if (AAID) {
            addItem(frm, "AAID", AAID);
        }
        ;
        if (d) {
            addItem(frm, "d", d);
        }
        ;
        if (v) {
            addItem(frm, "v", v);
        }
        ;
        if (IMEI) {
            addItem(frm, "IMEI", IMEI);
        }
        ;
        frm.action = action1;
        frm.target = "gionee_iframe";
        frm.submit();
        frm.removeAttribute("target");
        frm.removeAttribute("action");
        if (num == "9" || num == "10" || num == "12" || num == "13" || num == "1008") {
            if (num == "12" || num == "13") {
                if (num == "13") {
                    obj.setAttribute("href", obj.getAttribute("href").replaceByMyTag(arr3, arr4));
                    window.location.href = obj.getAttribute("href");
                    return false;
                } else {
                    window.location.href = obj.getAttribute("href");
                }
                ;
            } else {
                window.location.href = obj.getAttribute("href")
            }
            ;
        }
        ;
    };


    function addItem(form, name, value) {
        var oD = document.createElement("input");
        oD.type = "hidden";
        oD.name = name;
        oD.value = value;
        form.appendChild(oD);
    };
    function getQueryString(name) {
        if (location.href.indexOf("@") != "-1") {
            var items = location.href.split("@")[1], item = null, i = 0, len = items.split("&").length;
            for (i = 0; i < len; i++) {
                item = items.split("&")[i].split("=");
                if (decodeURIComponent(item[0]) == name) {
                    return decodeURIComponent(item[1]);
                }
            }
        }
        ;
    };
    function getName(str) {
        try {
            return str.split("/")[1].split(";")[2].split(" ")[1];
        } catch (e) {
            return "";
        }
    };
    function getVersion(str) {
        try {
            return str.split("/")[1].split(";")[1];
        } catch (e) {
            return "";
        }
    };
    function getQueryString1(name) {
        var qs = location.search.length > 0 ? location.search.substring(1) : "", items = qs.length ? qs.split("&") : [], item = null, i = 0, len = items.length;
        for (i = 0; i < len; i++) {
            item = items[i].split("=");
            if (decodeURIComponent(item[0]) == name) {
                return decodeURIComponent(item[1]);
            }
        }
    };
    if (document.getElementById("foot")) {
        document.getElementById("foot").style.fontSize = (document.documentElement.clientWidth > 640 ? 640 : document.documentElement.clientWidth) / 16 + "px";
        var fImg = document.getElementById("foot").getElementsByTagName("img")[0];
        if (!!fImg) {
            var nImg = new Image();
            nImg.src = fImg.src;
            nImg.onload = function () {
                document.body.style.paddingBottom = document.getElementById("foot").offsetHeight - 12 + "px";
            };
            nImg.onerror = function () {
                fImg.width = "13.5%";
                fImg.style.height = fImg.offsetWidth + "px";
                document.body.style.paddingBottom = document.getElementById("foot").offsetHeight - 12 + "px";
            };
        }
        ;
    }
    ;
    function getPageId() {
        try {
            var oLocation = window.location.href.split(".html")[0];
            return oLocation.substring(oLocation.lastIndexOf("/", oLocation.lastIndexOf("/") - 1) + 1, oLocation.lastIndexOf("/"));
        } catch (e) {
            return "";
        }
        ;
    };
    ;
    var promoteImgPicBtn = document.getElementsByClassName("promoteImgPic")[0];
    if (promoteImgPicBtn) {
        promoteImgPicBtn.onclick = function (e) {
            check(promoteImgPicBtn, "12");
            e.preventDefault();
            thirdCheck(promoteImgPicBtn);
        };
    }
    ;
    var promoteAppInFoot = document.getElementsByClassName("promoteAppInFoot")[0];
    if (promoteAppInFoot) {
        promoteAppInFoot.onclick = function () {
            check(promoteAppInFoot, "13");
            thirdCheck(promoteAppInFoot);
        };
    }
    ;
    function thirdCheck(obj) {
        if (obj.dataset.url) {
            var urlArr = obj.dataset.url.split(",");
            if (urlArr.length) {
                for (var i = 0; i < urlArr.length; i++) {
                    var oForm = document.createElement("form");
                    oForm.id = "promote_form" + i;
                    oForm.name = "promote_form" + i;
                    oForm.className = "promote_form" + i;
                    oForm.method = "post";
                    var oForm2 = document.getElementsByClassName("promote_form" + i)[0];
                    if (!oForm2) {
                        document.body.appendChild(oForm);
                    }
                    ;
                    var oIframe = document.createElement("iframe");
                    oIframe.id = "promote_iframe" + i;
                    oIframe.name = "promote_iframe" + i;
                    oIframe.className = "promote_iframe" + i;
                    oIframe.style.width = "1";
                    oIframe.style.height = "1";
                    oIframe.style.display = "none";
                    var oIframe2 = document.getElementsByClassName("promote_iframe" + i)[0];
                    if (!oIframe2) {
                        document.body.appendChild(oIframe);
                    }
                    ;
                    oForm.action = urlArr[i];
                    oForm.target = "promote_iframe" + i;

                    oForm.submit();
                }
                ;
            }
            ;
        }
        ;
    };
    var sa = getQueryString("sa") || getQueryString1("sa");
    if (sa == "0") {
        var oPao = document.getElementById("promoteAppOut");
        if (oPao) {
            oPao.style.display = "none";
        }
        ;
    }
    ;
    var oA = document.getElementsByTagName("a");
    Array.prototype.forEach.call(oA, function (item) {
        if (item.firstChild.nodeName.toLowerCase() === "img" && item.getAttribute("title")) {
            item.onclick = function (e) {
                e.preventDefault();
                check(item, "1008");
                return false;
            };
        }
        ;
    });
    var aTables = document.querySelectorAll("table");
    if (aTables.length) {
        Array.prototype.forEach.call(aTables, function (item) {
            wrapper(item, "div");
        });
    }
    ;
    function wrapper(node, tagName) {
        var oDiv = document.createElement(tagName);
        oDiv.style.overflowX = "auto";
        oDiv.style.width = "100%";
        oDiv.innerHTML = "<" + node.nodeName.toLowerCase() + " style='width:auto !important;'>" + node.innerHTML + "</" + node.nodeName.toLowerCase() + ">";
        node.parentNode.replaceChild(oDiv, node);
    };
}, !1);
