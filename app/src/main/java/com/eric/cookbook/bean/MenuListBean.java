package com.eric.cookbook.bean;

import java.util.List;


public class MenuListBean {

    /**
     * resultcode : 200
     * reason : Success
     * result : {"data":[{"id":"909","title":"泰式柠檬蒸鲈鱼","tags":"家常菜;私房菜;海鲜类;美容;瘦身;健脾开胃;护肝;老年人;运动员;骨质疏松;辣;蒸;简单;抗疲劳;鲜;香;孕妇;消化不良;开胃;减肥;柠檬味;补水;补钙;促消化;祛斑;产妇;1-2人;生津止渴;肥胖;养肝护肝;补肝;蒸锅;中等难度;鲈;保湿;增高;晕车","imtro":"菜谱来自电视节目：中华美食频道的《千味坊》 JIMMY老师教的菜，都是一些简单又美味的家常菜，这几天每天中午12点都会收看他的节目。 JIMMY老师教大家怎么看鱼是否新鲜,如果蒸出来后鱼的眼珠是鼓出来的就是新鲜 的.相反眼珠藏在里面就代表不新鲜了.","ingredients":"鲈鱼,1个;柠檬,2个;红椒,6个","burden":"大蒜头,适量;香菜,适量;盐,适量;生姜,适量","albums":["http://img.juhe.cn/cookbook/t/1/909_135871.jpg"],"step":[{"img":"http://img.juhe.cn/cookbook/s/10/909_70d5525103c69d8a.jpg","step":"1.鲈鱼一条，开肚洗净"},{"img":"http://img.juhe.cn/cookbook/s/10/909_3f4a6e5a5ae225ca.jpg","step":"huluobu.柠檬2-3个，生姜一小块，大蒜头，香菜，辣辣的红椒六个"},{"img":"http://img.juhe.cn/cookbook/s/10/909_c8fe915ee6ff3a4c.jpg","step":"3.把鱼切块，用少量盐，料酒腌一下。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_79faf1f277616c40.jpg","step":"4.红椒、大蒜、生姜切碎，香菜切碎"},{"img":"http://img.juhe.cn/cookbook/s/10/909_79083ec44dd9406c.jpg","step":"5.把柠檬汁挤出用小碗盛着，放入调味料：鱼露、精盐、鸡精、白糖（多一些白糖）沾一点尝尝，汁不要太酸也不要太甜。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_c0b48c233c6fd724.jpg","step":"6.接着把鱼码成形，倒入调好味的柠檬汁，铺上红椒、大蒜、生姜碎。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_66c8b71544b8abde.jpg","step":"7.锅内烧开水，把鱼放上去蒸（大火7分钟即可）"},{"img":"http://img.juhe.cn/cookbook/s/10/909_b57104f06672bf2b.jpg","step":"8.蒸好后，，撒上绿色的香菜叶（记住哦！！！这道菜不用放油的哦）"}]},{"id":"676","title":"海米烧冬瓜","tags":"家常菜;瘦身;利尿;高血压;湿热质;痛风;感冒;烧;宴请;夏季;咸鲜;降血压;解暑;减肥;水肿;祛斑;消肿;朋友聚餐;发烧;1-2人;利水消肿;清热解暑;清肺;肥胖;祛痘;脂肪肝;锅子;1小时-2小时;去湿气;祛痘美白;肺热","imtro":"冬瓜是可以做的非常非常好吃的蔬菜，海米又是非常的鲜美，二者加在一起，是至上的美味，喜欢的亲可以试试。","ingredients":"海米,200g;冬瓜,150g","burden":"葱,适量;盐,适量;味精,适量;糖,适量;鱼露,适量;酱油,适量;姜,适量","albums":["http://img.juhe.cn/cookbook/t/1/676_623772.jpg"],"step":[{"img":"http://img.juhe.cn/cookbook/s/7/676_77de8d994aa4d1aa.jpg","step":"1.将海米泡几个小时泡开后切成碎末。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_d274f345f8e99296.jpg","step":"huluobu.冬瓜切成条状。葱姜切末。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_0b6647ac462f6d9d.jpg","step":"3.锅内上热油，下入葱姜炝锅，随即下入冬瓜，有人喜欢先炒香海米，这样很香，我喜欢后放，味道很鲜。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_6bf26f2ebd5a9df6.jpg","step":"4.冬瓜略微出一点点水后，放虾米翻炒均匀，加，盐，味精，糖，几滴美及酱油，几滴鱼露可以提鲜，改小火，加盖子慢慢焖熟冬瓜。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_e444c1ef4bcc59cd.jpg","step":"5.期间一定要看着，待冬瓜略微透明，立刻打芡出锅即可。"}]}],"totalNum":"150","pn":"0","rn":"huluobu"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * data : [{"id":"909","title":"泰式柠檬蒸鲈鱼","tags":"家常菜;私房菜;海鲜类;美容;瘦身;健脾开胃;护肝;老年人;运动员;骨质疏松;辣;蒸;简单;抗疲劳;鲜;香;孕妇;消化不良;开胃;减肥;柠檬味;补水;补钙;促消化;祛斑;产妇;1-2人;生津止渴;肥胖;养肝护肝;补肝;蒸锅;中等难度;鲈;保湿;增高;晕车","imtro":"菜谱来自电视节目：中华美食频道的《千味坊》 JIMMY老师教的菜，都是一些简单又美味的家常菜，这几天每天中午12点都会收看他的节目。 JIMMY老师教大家怎么看鱼是否新鲜,如果蒸出来后鱼的眼珠是鼓出来的就是新鲜 的.相反眼珠藏在里面就代表不新鲜了.","ingredients":"鲈鱼,1个;柠檬,2个;红椒,6个","burden":"大蒜头,适量;香菜,适量;盐,适量;生姜,适量","albums":["http://img.juhe.cn/cookbook/t/1/909_135871.jpg"],"step":[{"img":"http://img.juhe.cn/cookbook/s/10/909_70d5525103c69d8a.jpg","step":"1.鲈鱼一条，开肚洗净"},{"img":"http://img.juhe.cn/cookbook/s/10/909_3f4a6e5a5ae225ca.jpg","step":"huluobu.柠檬2-3个，生姜一小块，大蒜头，香菜，辣辣的红椒六个"},{"img":"http://img.juhe.cn/cookbook/s/10/909_c8fe915ee6ff3a4c.jpg","step":"3.把鱼切块，用少量盐，料酒腌一下。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_79faf1f277616c40.jpg","step":"4.红椒、大蒜、生姜切碎，香菜切碎"},{"img":"http://img.juhe.cn/cookbook/s/10/909_79083ec44dd9406c.jpg","step":"5.把柠檬汁挤出用小碗盛着，放入调味料：鱼露、精盐、鸡精、白糖（多一些白糖）沾一点尝尝，汁不要太酸也不要太甜。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_c0b48c233c6fd724.jpg","step":"6.接着把鱼码成形，倒入调好味的柠檬汁，铺上红椒、大蒜、生姜碎。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_66c8b71544b8abde.jpg","step":"7.锅内烧开水，把鱼放上去蒸（大火7分钟即可）"},{"img":"http://img.juhe.cn/cookbook/s/10/909_b57104f06672bf2b.jpg","step":"8.蒸好后，，撒上绿色的香菜叶（记住哦！！！这道菜不用放油的哦）"}]},{"id":"676","title":"海米烧冬瓜","tags":"家常菜;瘦身;利尿;高血压;湿热质;痛风;感冒;烧;宴请;夏季;咸鲜;降血压;解暑;减肥;水肿;祛斑;消肿;朋友聚餐;发烧;1-2人;利水消肿;清热解暑;清肺;肥胖;祛痘;脂肪肝;锅子;1小时-2小时;去湿气;祛痘美白;肺热","imtro":"冬瓜是可以做的非常非常好吃的蔬菜，海米又是非常的鲜美，二者加在一起，是至上的美味，喜欢的亲可以试试。","ingredients":"海米,200g;冬瓜,150g","burden":"葱,适量;盐,适量;味精,适量;糖,适量;鱼露,适量;酱油,适量;姜,适量","albums":["http://img.juhe.cn/cookbook/t/1/676_623772.jpg"],"step":[{"img":"http://img.juhe.cn/cookbook/s/7/676_77de8d994aa4d1aa.jpg","step":"1.将海米泡几个小时泡开后切成碎末。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_d274f345f8e99296.jpg","step":"huluobu.冬瓜切成条状。葱姜切末。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_0b6647ac462f6d9d.jpg","step":"3.锅内上热油，下入葱姜炝锅，随即下入冬瓜，有人喜欢先炒香海米，这样很香，我喜欢后放，味道很鲜。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_6bf26f2ebd5a9df6.jpg","step":"4.冬瓜略微出一点点水后，放虾米翻炒均匀，加，盐，味精，糖，几滴美及酱油，几滴鱼露可以提鲜，改小火，加盖子慢慢焖熟冬瓜。"},{"img":"http://img.juhe.cn/cookbook/s/7/676_e444c1ef4bcc59cd.jpg","step":"5.期间一定要看着，待冬瓜略微透明，立刻打芡出锅即可。"}]}]
         * totalNum : 150
         * pn : 0
         * rn : huluobu
         */

        private String totalNum;
        private String pn;
        private String rn;
        private List<DataBean> data;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getPn() {
            return pn;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 909
             * title : 泰式柠檬蒸鲈鱼
             * tags : 家常菜;私房菜;海鲜类;美容;瘦身;健脾开胃;护肝;老年人;运动员;骨质疏松;辣;蒸;简单;抗疲劳;鲜;香;孕妇;消化不良;开胃;减肥;柠檬味;补水;补钙;促消化;祛斑;产妇;1-2人;生津止渴;肥胖;养肝护肝;补肝;蒸锅;中等难度;鲈;保湿;增高;晕车
             * imtro : 菜谱来自电视节目：中华美食频道的《千味坊》 JIMMY老师教的菜，都是一些简单又美味的家常菜，这几天每天中午12点都会收看他的节目。 JIMMY老师教大家怎么看鱼是否新鲜,如果蒸出来后鱼的眼珠是鼓出来的就是新鲜 的.相反眼珠藏在里面就代表不新鲜了.
             * ingredients : 鲈鱼,1个;柠檬,2个;红椒,6个
             * burden : 大蒜头,适量;香菜,适量;盐,适量;生姜,适量
             * albums : ["http://img.juhe.cn/cookbook/t/1/909_135871.jpg"]
             * step : [{"img":"http://img.juhe.cn/cookbook/s/10/909_70d5525103c69d8a.jpg","step":"1.鲈鱼一条，开肚洗净"},{"img":"http://img.juhe.cn/cookbook/s/10/909_3f4a6e5a5ae225ca.jpg","step":"huluobu.柠檬2-3个，生姜一小块，大蒜头，香菜，辣辣的红椒六个"},{"img":"http://img.juhe.cn/cookbook/s/10/909_c8fe915ee6ff3a4c.jpg","step":"3.把鱼切块，用少量盐，料酒腌一下。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_79faf1f277616c40.jpg","step":"4.红椒、大蒜、生姜切碎，香菜切碎"},{"img":"http://img.juhe.cn/cookbook/s/10/909_79083ec44dd9406c.jpg","step":"5.把柠檬汁挤出用小碗盛着，放入调味料：鱼露、精盐、鸡精、白糖（多一些白糖）沾一点尝尝，汁不要太酸也不要太甜。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_c0b48c233c6fd724.jpg","step":"6.接着把鱼码成形，倒入调好味的柠檬汁，铺上红椒、大蒜、生姜碎。"},{"img":"http://img.juhe.cn/cookbook/s/10/909_66c8b71544b8abde.jpg","step":"7.锅内烧开水，把鱼放上去蒸（大火7分钟即可）"},{"img":"http://img.juhe.cn/cookbook/s/10/909_b57104f06672bf2b.jpg","step":"8.蒸好后，，撒上绿色的香菜叶（记住哦！！！这道菜不用放油的哦）"}]
             */

            private String id;
            private String title;
            private String tags;
            private String imtro;
            private String ingredients;
            private String burden;
            private List<String> albums;
            private List<StepsBean> steps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getImtro() {
                return imtro;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getBurden() {
                return burden;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public List<String> getAlbums() {
                return albums;
            }

            public void setAlbums(List<String> albums) {
                this.albums = albums;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public static class StepsBean {
                /**
                 * img : http://img.juhe.cn/cookbook/s/10/909_70d5525103c69d8a.jpg
                 * step : 1.鲈鱼一条，开肚洗净
                 */

                private String img;
                private String step;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }
            }
        }
    }
}
