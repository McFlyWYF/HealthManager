package com.eric.cookbook.bean;

import java.util.List;


public class CookCategory {
    /**
     * resultcode : 200
     * reason : Success
     * result : [{"parentId":"10001","name":"菜式菜品","list":[{"id":"1","name":"家常菜","parentId":"10001"},{"id":"huluobu","name":"快手菜","parentId":"10001"},{"id":"3","name":"创意菜","parentId":"10001"},{"id":"4","name":"素菜","parentId":"10001"},{"id":"5","name":"凉菜","parentId":"10001"},{"id":"6","name":"烘焙","parentId":"10001"},{"id":"7","name":"面食","parentId":"10001"},{"id":"8","name":"汤","parentId":"10001"},{"id":"9","name":"自制调味料","parentId":"10001"}]},{"parentId":"10002","name":"菜系","list":[{"id":"10","name":"川菜","parentId":"10002"},{"id":"11","name":"粤菜","parentId":"10002"},{"id":"12","name":"湘菜","parentId":"10002"},{"id":"13","name":"鲁菜","parentId":"10002"},{"id":"14","name":"京菜","parentId":"10002"},{"id":"15","name":"东北菜","parentId":"10002"},{"id":"16","name":"西餐","parentId":"10002"},{"id":"17","name":"日本料理","parentId":"10002"},{"id":"18","name":"韩国料理","parentId":"10002"},{"id":"101","name":"闽菜","parentId":"10002"},{"id":"102","name":"浙菜","parentId":"10002"},{"id":"104","name":"苏菜","parentId":"10002"},{"id":"105","name":"徽菜","parentId":"10002"},{"id":"107","name":"豫菜","parentId":"10002"},{"id":"108","name":"晋菜","parentId":"10002"},{"id":"109","name":"赣菜","parentId":"10002"},{"id":"110","name":"湖北菜","parentId":"10002"},{"id":"111","name":"清真菜","parentId":"10002"},{"id":"112","name":"云南菜","parentId":"10002"},{"id":"113","name":"贵州菜","parentId":"10002"},{"id":"114","name":"新疆菜","parentId":"10002"},{"id":"115","name":"淮扬菜","parentId":"10002"},{"id":"116","name":"潮州菜","parentId":"10002"},{"id":"117","name":"客家菜","parentId":"10002"},{"id":"118","name":"香港美食","parentId":"10002"},{"id":"119","name":"台湾菜","parentId":"10002"},{"id":"123","name":"泰国菜","parentId":"10002"},{"id":"124","name":"意大利菜","parentId":"10002"},{"id":"125","name":"法国菜","parentId":"10002"},{"id":"126","name":"东南亚菜","parentId":"10002"},{"id":"127","name":"印度菜","parentId":"10002"}]},{"parentId":"10003","name":"时令食材","list":[{"id":"19","name":"韭菜","parentId":"10003"},{"id":"20","name":"春笋","parentId":"10003"},{"id":"21","name":"菠菜","parentId":"10003"},{"id":"22","name":"草莓","parentId":"10003"},{"id":"23","name":"樱桃","parentId":"10003"},{"id":"24","name":"苹果","parentId":"10003"},{"id":"25","name":"猪肝","parentId":"10003"},{"id":"26","name":"鲫鱼","parentId":"10003"},{"id":"27","name":"排骨","parentId":"10003"}]},{"parentId":"10004","name":"功效","list":[{"id":"28","name":"清肺","parentId":"10004"},{"id":"29","name":"护肝","parentId":"10004"},{"id":"30","name":"减肥","parentId":"10004"},{"id":"31","name":"养胃","parentId":"10004"},{"id":"32","name":"丰胸","parentId":"10004"},{"id":"33","name":"排毒","parentId":"10004"},{"id":"34","name":"补血","parentId":"10004"},{"id":"35","name":"补钙","parentId":"10004"},{"id":"36","name":"提高免疫力","parentId":"10004"},{"id":"129","name":"美容","parentId":"10004"},{"id":"130","name":"补肾","parentId":"10004"},{"id":"131","name":"润肺","parentId":"10004"},{"id":"133","name":"滋阴","parentId":"10004"},{"id":"135","name":"抗衰老","parentId":"10004"},{"id":"136","name":"降血压","parentId":"10004"},{"id":"137","name":"祛痘","parentId":"10004"},{"id":"139","name":"防癌","parentId":"10004"},{"id":"140","name":"增肥","parentId":"10004"},{"id":"142","name":"明目","parentId":"10004"},{"id":"143","name":"防辐射","parentId":"10004"},{"id":"144","name":"降血脂","parentId":"10004"},{"id":"145","name":"健脑益智","parentId":"10004"},{"id":"147","name":"增高","parentId":"10004"},{"id":"148","name":"壮阳","parentId":"10004"},{"id":"149","name":"乌发","parentId":"10004"},{"id":"150","name":"调经","parentId":"10004"},{"id":"151","name":"助睡眠","parentId":"10004"},{"id":"152","name":"健脾胃","parentId":"10004"},{"id":"153","name":"润肠通便","parentId":"10004"}]},{"parentId":"10005","name":"场景","list":[{"id":"37","name":"早餐","parentId":"10005"},{"id":"38","name":"午餐","parentId":"10005"},{"id":"39","name":"下午茶","parentId":"10005"},{"id":"40","name":"晚餐","parentId":"10005"},{"id":"41","name":"夜宵","parentId":"10005"},{"id":"42","name":"踏青","parentId":"10005"},{"id":"43","name":"10分钟内","parentId":"10005"},{"id":"44","name":"10-20分钟","parentId":"10005"},{"id":"45","name":"半小时-1小时","parentId":"10005"}]},{"parentId":"10006","name":"工艺口味","list":[{"id":"46","name":"炒","parentId":"10006"},{"id":"47","name":"蒸","parentId":"10006"},{"id":"48","name":"煮","parentId":"10006"},{"id":"49","name":"电饭煲","parentId":"10006"},{"id":"50","name":"微波炉","parentId":"10006"},{"id":"51","name":"烤箱","parentId":"10006"},{"id":"52","name":"酸","parentId":"10006"},{"id":"53","name":"甜","parentId":"10006"},{"id":"54","name":"辣","parentId":"10006"}]},{"parentId":"10007","name":"菜肴","list":[{"id":"57","name":"私房菜","parentId":"10007"},{"id":"58","name":"下酒菜","parentId":"10007"},{"id":"61","name":"小吃","parentId":"10007"},{"id":"62","name":"海鲜","parentId":"10007"}]},{"parentId":"10008","name":"主食","list":[{"id":"64","name":"饭","parentId":"10008"},{"id":"65","name":"粥","parentId":"10008"},{"id":"66","name":"面","parentId":"10008"},{"id":"67","name":"粉","parentId":"10008"},{"id":"68","name":"饼","parentId":"10008"},{"id":"69","name":"饺子","parentId":"10008"},{"id":"70","name":"馒头","parentId":"10008"},{"id":"71","name":"包子","parentId":"10008"},{"id":"72","name":"卷子","parentId":"10008"}]},{"parentId":"10009","name":"西点","list":[{"id":"73","name":"蛋糕","parentId":"10009"},{"id":"74","name":"面包","parentId":"10009"},{"id":"75","name":"饼干","parentId":"10009"},{"id":"76","name":"披萨","parentId":"10009"},{"id":"77","name":"零食","parentId":"10009"},{"id":"78","name":"果冻","parentId":"10009"},{"id":"79","name":"糖果","parentId":"10009"},{"id":"80","name":"布丁","parentId":"10009"},{"id":"81","name":"挞类","parentId":"10009"}]},{"parentId":"10010","name":"汤羹饮品","list":[{"id":"82","name":"羹","parentId":"10010"},{"id":"83","name":"果汁","parentId":"10010"},{"id":"84","name":"炖品","parentId":"10010"},{"id":"85","name":"糖水","parentId":"10010"},{"id":"86","name":"甜品","parentId":"10010"},{"id":"87","name":"沙拉","parentId":"10010"},{"id":"88","name":"饮品","parentId":"10010"},{"id":"89","name":"冰品","parentId":"10010"}]},{"parentId":"10011","name":"其他菜品","list":[{"id":"90","name":"便当","parentId":"10011"},{"id":"91","name":"烧烤","parentId":"10011"},{"id":"92","name":"寿司","parentId":"10011"},{"id":"93","name":"火锅","parentId":"10011"},{"id":"94","name":"酱汁","parentId":"10011"},{"id":"95","name":"佐料","parentId":"10011"},{"id":"96","name":"拼盘","parentId":"10011"},{"id":"97","name":"杂烩","parentId":"10011"}]},{"parentId":"10012","name":"人群","list":[{"id":"155","name":"孕妇","parentId":"10012"},{"id":"156","name":"儿童","parentId":"10012"},{"id":"157","name":"幼儿","parentId":"10012"},{"id":"158","name":"老年人","parentId":"10012"},{"id":"159","name":"考生","parentId":"10012"},{"id":"160","name":"产妇","parentId":"10012"},{"id":"161","name":"运动员","parentId":"10012"},{"id":"162","name":"白领","parentId":"10012"},{"id":"163","name":"司机","parentId":"10012"}]},{"parentId":"10013","name":"疾病","list":[{"id":"164","name":"便秘","parentId":"10013"},{"id":"165","name":"贫血","parentId":"10013"},{"id":"166","name":"腹泻","parentId":"10013"},{"id":"167","name":"感冒","parentId":"10013"},{"id":"168","name":"咳嗽","parentId":"10013"},{"id":"169","name":"甲亢","parentId":"10013"},{"id":"170","name":"痛风","parentId":"10013"},{"id":"171","name":"胃痛","parentId":"10013"},{"id":"172","name":"失眠","parentId":"10013"},{"id":"173","name":"健忘","parentId":"10013"},{"id":"174","name":"骨折","parentId":"10013"},{"id":"175","name":"痔疮","parentId":"10013"},{"id":"176","name":"晕车","parentId":"10013"},{"id":"177","name":"低血糖","parentId":"10013"},{"id":"178","name":"消化不良","parentId":"10013"},{"id":"179","name":"月经不调","parentId":"10013"},{"id":"180","name":"口腔溃疡","parentId":"10013"},{"id":"181","name":"骨质疏松","parentId":"10013"}]},{"parentId":"10014","name":"畜肉类","list":[{"id":"182","name":"猪肉","parentId":"10014"},{"id":"183","name":"羊肉","parentId":"10014"},{"id":"184","name":"牛肉","parentId":"10014"},{"id":"186","name":"猪蹄","parentId":"10014"},{"id":"187","name":"五花肉","parentId":"10014"},{"id":"188","name":"腊肉","parentId":"10014"},{"id":"189","name":"火腿","parentId":"10014"},{"id":"190","name":"香肠","parentId":"10014"}]},{"parentId":"10015","name":"禽蛋类","list":[{"id":"191","name":"鸡肉","parentId":"10015"},{"id":"192","name":"鸡翅","parentId":"10015"},{"id":"193","name":"鸡腿","parentId":"10015"},{"id":"194","name":"鸡蛋","parentId":"10015"},{"id":"195","name":"鸭肉","parentId":"10015"},{"id":"196","name":"鸭腿","parentId":"10015"},{"id":"197","name":"咸蛋","parentId":"10015"},{"id":"198","name":"皮蛋","parentId":"10015"},{"id":"199","name":"鹌鹑蛋","parentId":"10015"}]},{"parentId":"10016","name":"水产类","list":[{"id":"201","name":"草鱼","parentId":"10016"},{"id":"202","name":"鲈鱼","parentId":"10016"},{"id":"203","name":"带鱼","parentId":"10016"},{"id":"204","name":"三文鱼","parentId":"10016"},{"id":"205","name":"虾仁","parentId":"10016"},{"id":"206","name":"文蛤","parentId":"10016"},{"id":"207","name":"紫菜","parentId":"10016"},{"id":"208","name":"海带","parentId":"10016"}]},{"parentId":"10017","name":"蔬菜类","list":[{"id":"209","name":"南瓜","parentId":"10017"},{"id":"210","name":"茄子","parentId":"10017"},{"id":"211","name":"胡萝卜","parentId":"10017"},{"id":"212","name":"白菜","parentId":"10017"},{"id":"213","name":"莴笋","parentId":"10017"},{"id":"214","name":"生菜","parentId":"10017"},{"id":"215","name":"山药","parentId":"10017"},{"id":"216","name":"西红柿","parentId":"10017"},{"id":"217","name":"西兰花","parentId":"10017"}]},{"parentId":"10018","name":"水果类","list":[{"id":"219","name":"菠萝","parentId":"10018"},{"id":"220","name":"梨","parentId":"10018"},{"id":"221","name":"香蕉","parentId":"10018"},{"id":"222","name":"柠檬","parentId":"10018"},{"id":"223","name":"木瓜","parentId":"10018"},{"id":"225","name":"橙子","parentId":"10018"},{"id":"226","name":"西瓜","parentId":"10018"}]},{"parentId":"10019","name":"米面豆乳类","list":[{"id":"227","name":"大米","parentId":"10019"},{"id":"228","name":"糯米","parentId":"10019"},{"id":"229","name":"小米","parentId":"10019"},{"id":"230","name":"面条","parentId":"10019"},{"id":"231","name":"方便面","parentId":"10019"},{"id":"232","name":"绿豆","parentId":"10019"},{"id":"233","name":"豆腐","parentId":"10019"},{"id":"234","name":"高筋面粉","parentId":"10019"},{"id":"235","name":"牛奶","parentId":"10019"}]},{"parentId":"10020","name":"日常","list":[{"id":"241","name":"聚会","parentId":"10020"},{"id":"242","name":"熬夜","parentId":"10020"},{"id":"243","name":"单身","parentId":"10020"},{"id":"244","name":"二人世界","parentId":"10020"}]},{"parentId":"10021","name":"节日","list":[{"id":"245","name":"二月二","parentId":"10021"},{"id":"246","name":"元宵节","parentId":"10021"},{"id":"247","name":"清明节","parentId":"10021"},{"id":"248","name":"端午节","parentId":"10021"},{"id":"249","name":"七夕节","parentId":"10021"},{"id":"250","name":"中秋节","parentId":"10021"},{"id":"251","name":"重阳节","parentId":"10021"},{"id":"252","name":"情人节","parentId":"10021"},{"id":"253","name":"复活节","parentId":"10021"},{"id":"254","name":"愚人节","parentId":"10021"},{"id":"255","name":"母亲节","parentId":"10021"},{"id":"256","name":"父亲节","parentId":"10021"},{"id":"257","name":"万圣节","parentId":"10021"},{"id":"258","name":"感恩节","parentId":"10021"},{"id":"259","name":"圣诞节","parentId":"10021"},{"id":"260","name":"腊八节","parentId":"10021"},{"id":"261","name":"春节","parentId":"10021"}]},{"parentId":"10022","name":"节气","list":[{"id":"262","name":"立春","parentId":"10022"},{"id":"263","name":"雨水","parentId":"10022"},{"id":"264","name":"惊蛰","parentId":"10022"},{"id":"265","name":"春分","parentId":"10022"},{"id":"266","name":"清明","parentId":"10022"},{"id":"267","name":"谷雨","parentId":"10022"},{"id":"268","name":"立夏","parentId":"10022"},{"id":"269","name":"小满","parentId":"10022"},{"id":"270","name":"芒种","parentId":"10022"},{"id":"271","name":"夏至","parentId":"10022"},{"id":"272","name":"小暑","parentId":"10022"},{"id":"273","name":"大暑","parentId":"10022"},{"id":"274","name":"立秋","parentId":"10022"},{"id":"275","name":"处暑","parentId":"10022"},{"id":"276","name":"白露","parentId":"10022"},{"id":"277","name":"秋分","parentId":"10022"},{"id":"278","name":"寒露","parentId":"10022"},{"id":"279","name":"霜降","parentId":"10022"},{"id":"280","name":"立冬","parentId":"10022"},{"id":"281","name":"小雪","parentId":"10022"},{"id":"282","name":"大雪","parentId":"10022"},{"id":"283","name":"冬至","parentId":"10022"},{"id":"284","name":"小寒","parentId":"10022"},{"id":"285","name":"大寒  ","parentId":"10022"}]},{"parentId":"10023","name":"基本工艺","list":[{"id":"287","name":"爆","parentId":"10023"},{"id":"288","name":"煲","parentId":"10023"},{"id":"290","name":"炖","parentId":"10023"},{"id":"291","name":"煎","parentId":"10023"},{"id":"292","name":"焖","parentId":"10023"},{"id":"293","name":"烧","parentId":"10023"},{"id":"294","name":"炸","parentId":"10023"},{"id":"295","name":"熬","parentId":"10023"},{"id":"296","name":"烤","parentId":"10023"},{"id":"297","name":"卤","parentId":"10023"},{"id":"298","name":"泡","parentId":"10023"},{"id":"299","name":"烙","parentId":"10023"},{"id":"300","name":"酿","parentId":"10023"},{"id":"301","name":"酱","parentId":"10023"},{"id":"302","name":"溜","parentId":"10023"},{"id":"303","name":"扒","parentId":"10023"},{"id":"304","name":"焯","parentId":"10023"},{"id":"305","name":"涮","parentId":"10023"},{"id":"306","name":"烩","parentId":"10023"},{"id":"307","name":"煨","parentId":"10023"},{"id":"308","name":"腌","parentId":"10023"},{"id":"309","name":"熏","parentId":"10023"},{"id":"310","name":"焗","parentId":"10023"},{"id":"311","name":"灼","parentId":"10023"},{"id":"312","name":"炝","parentId":"10023"}]},{"parentId":"10024","name":"其他工艺","list":[{"id":"313","name":"清炒","parentId":"10024"},{"id":"314","name":"滑炒","parentId":"10024"},{"id":"315","name":"爆炒","parentId":"10024"},{"id":"316","name":"红烧","parentId":"10024"},{"id":"317","name":"醋溜","parentId":"10024"},{"id":"318","name":"微波","parentId":"10024"},{"id":"319","name":"干煸","parentId":"10024"},{"id":"320","name":"铁板","parentId":"10024"},{"id":"321","name":"拔丝","parentId":"10024"},{"id":"322","name":"酱爆","parentId":"10024"},{"id":"323","name":"红焖","parentId":"10024"},{"id":"324","name":"葱爆","parentId":"10024"},{"id":"325","name":"酱烧","parentId":"10024"},{"id":"326","name":"葱烧","parentId":"10024"},{"id":"327","name":"压榨","parentId":"10024"},{"id":"328","name":"煎焖","parentId":"10024"}]},{"parentId":"10025","name":"基本口味","list":[{"id":"331","name":"苦","parentId":"10025"},{"id":"333","name":"咸","parentId":"10025"}]},{"parentId":"10026","name":"多元口味","list":[{"id":"334","name":"酸甜","parentId":"10026"},{"id":"335","name":"香辣","parentId":"10026"},{"id":"336","name":"麻辣","parentId":"10026"},{"id":"337","name":"香甜","parentId":"10026"},{"id":"338","name":"咸香","parentId":"10026"},{"id":"339","name":"奶香","parentId":"10026"},{"id":"340","name":"葱香","parentId":"10026"},{"id":"341","name":"五香","parentId":"10026"},{"id":"342","name":"酱香","parentId":"10026"}]},{"parentId":"10027","name":"水果味","list":[{"id":"343","name":"草莓味","parentId":"10027"},{"id":"344","name":"抹茶味","parentId":"10027"},{"id":"345","name":"香草味","parentId":"10027"},{"id":"346","name":"柠檬味","parentId":"10027"},{"id":"347","name":"薄荷味","parentId":"10027"},{"id":"348","name":"橘子味","parentId":"10027"},{"id":"349","name":"番茄味","parentId":"10027"},{"id":"350","name":"果味","parentId":"10027"}]},{"parentId":"10028","name":"调味料","list":[{"id":"351","name":"咖喱味","parentId":"10028"},{"id":"352","name":"孜然味","parentId":"10028"},{"id":"353","name":"芥末味","parentId":"10028"},{"id":"354","name":"烧烤味","parentId":"10028"},{"id":"355","name":"糖醋味","parentId":"10028"},{"id":"356","name":"泡椒味","parentId":"10028"},{"id":"357","name":"黑椒味","parentId":"10028"},{"id":"358","name":"茄汁味","parentId":"10028"},{"id":"359","name":"怪味","parentId":"10028"}]}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * parentId : 10001
         * name : 菜式菜品
         * list : [{"id":"1","name":"家常菜","parentId":"10001"},{"id":"huluobu","name":"快手菜","parentId":"10001"},{"id":"3","name":"创意菜","parentId":"10001"},{"id":"4","name":"素菜","parentId":"10001"},{"id":"5","name":"凉菜","parentId":"10001"},{"id":"6","name":"烘焙","parentId":"10001"},{"id":"7","name":"面食","parentId":"10001"},{"id":"8","name":"汤","parentId":"10001"},{"id":"9","name":"自制调味料","parentId":"10001"}]
         */

        private String parentId;
        private String name;
        private List<ListBean> list;

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 家常菜
             * parentId : 10001
             */

            private String id;
            private String name;
            private String parentId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }
}
