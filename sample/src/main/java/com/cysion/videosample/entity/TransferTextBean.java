package com.cysion.videosample.entity;

import java.io.Serializable;
import java.util.List;

public class TransferTextBean implements Serializable {

    /**
     * cn : {"st":{"bg":"13580","ed":"0","rt":[{"ws":[{"cw":[{"w":"低头","wp":"n"}],"wb":0,"we":0}]}],"type":"1"}}
     * seg_id : 13
     */

    private CnBean cn;
    private int seg_id;

    public CnBean getCn() {
        return cn;
    }

    public void setCn(CnBean cn) {
        this.cn = cn;
    }

    public int getSeg_id() {
        return seg_id;
    }

    public void setSeg_id(int seg_id) {
        this.seg_id = seg_id;
    }

    public static class CnBean {
        /**
         * st : {"bg":"13580","ed":"0","rt":[{"ws":[{"cw":[{"w":"低头","wp":"n"}],"wb":0,"we":0}]}],"type":"1"}
         */

        private StBean st;

        public StBean getSt() {
            return st;
        }

        public void setSt(StBean st) {
            this.st = st;
        }

        public static class StBean {
            /**
             * bg : 13580
             * ed : 0
             * rt : [{"ws":[{"cw":[{"w":"低头","wp":"n"}],"wb":0,"we":0}]}]
             * type : 1
             */

            private String bg;
            private String ed;
            private String type;
            private List<RtBean> rt;

            public String getBg() {
                return bg;
            }

            public void setBg(String bg) {
                this.bg = bg;
            }

            public String getEd() {
                return ed;
            }

            public void setEd(String ed) {
                this.ed = ed;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<RtBean> getRt() {
                return rt;
            }

            public void setRt(List<RtBean> rt) {
                this.rt = rt;
            }

            public static class RtBean {
                private List<WsBean> ws;

                public List<WsBean> getWs() {
                    return ws;
                }

                public void setWs(List<WsBean> ws) {
                    this.ws = ws;
                }

                public static class WsBean {
                    /**
                     * cw : [{"w":"低头","wp":"n"}]
                     * wb : 0
                     * we : 0
                     */

                    private int wb;
                    private int we;
                    private List<CwBean> cw;

                    public int getWb() {
                        return wb;
                    }

                    public void setWb(int wb) {
                        this.wb = wb;
                    }

                    public int getWe() {
                        return we;
                    }

                    public void setWe(int we) {
                        this.we = we;
                    }

                    public List<CwBean> getCw() {
                        return cw;
                    }

                    public void setCw(List<CwBean> cw) {
                        this.cw = cw;
                    }

                    public static class CwBean {
                        /**
                         * w : 低头
                         * wp : n
                         */

                        private String w;
                        private String wp;

                        public String getW() {
                            return w;
                        }

                        public void setW(String w) {
                            this.w = w;
                        }

                        public String getWp() {
                            return wp;
                        }

                        public void setWp(String wp) {
                            this.wp = wp;
                        }
                    }
                }
            }
        }
    }
}
