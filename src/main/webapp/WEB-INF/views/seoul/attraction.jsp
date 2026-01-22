<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기 및 지도</title>
<link rel="stylesheet" href="/css/map.css" type="text/css">
<link rel="stylesheet" href="/css/toast.css" type="text/css">
<script>
    const SESSION_ID='${sessionScope.userid}'
    const CNO='${param.contentid}'
</script>
<style type="text/css">
.page-link:hover, .a-btn{
  cursor: pointer;
}
/* 지도 레이아웃 깨짐 방지 */
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
</style>
</head>
<body>
    <div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>${vo.title }</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row">
                <table class="table">
                    <tbody>
                        <tr>
                            <td width="30%" class="text-center" rowspan="6">
                                <img src="${vo.image1 }" style="width: 100%;height: 320px">
                            </td>
                            <td colspan="2"><h3>${vo.title}</h3></td>
                        </tr>
                        <tr><td width="15%" class="text-center">주소</td><td width="55%">${vo.address }</td></tr>
                        <tr><td width="15%" class="text-center">안내</td><td width="55%">${vo.avo.infocenter }</td></tr>
                        <tr><td width="15%" class="text-center">사용시간</td><td width="55%">${vo.avo.usetime }</td></tr>
                        <tr><td width="15%" class="text-center">휴무일</td><td width="55%">${vo.avo.restdate }</td></tr>
                        <tr><td width="15%" class="text-center">주차</td><td width="55%">${vo.avo.parking }</td></tr>
                    </tbody>
                </table>

                <table class="table">
                    <tbody>
                        <tr>
                            <td class="text-center">
                                <div class="map_wrap">
                                    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
                                    <div id="menu_wrap" class="bg_white">
                                        <div class="option">
                                            <div>
                                                <form onsubmit="searchPlaces(); return false;">
                                                    키워드 : <input type="text" value="${addr } 맛집" id="keyword" size="15"> 
                                                    <button type="submit">검색하기</button> 
                                                </form>
                                            </div>
                                        </div>
                                        <hr>
                                        <ul id="placesList"></ul>
                                        <div id="pagination"></div>
                                    </div>
                                </div>
                                
                                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7d1d66f384fdb4c354c90e65a97ae96e&libraries=services"></script>
                                <script src="/vue/map.js"></script>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <script src="/vue/axios.js"></script>
                <script src="/vue/reply/commonsReplyStore.js"></script>
                
                <div id="comment" style="width: 100%">
                    <div class="comment_area section_padding_50 clearfix">
                        <h4 class="mb-30">댓글 ({{store.count}})</h4>
                        <ol>
                            <li class="single_comment_area" v-for="(rvo,index) in store.list" :key="index">
                                <div class="comment-wrapper d-flex" v-if="rvo.group_tab===0">
                                    <div class="comment-author">
                                        <img :src="rvo.sex==='남자'?'/img/man.png':'/img/woman.png'">
                                    </div>
                                    <div class="comment-content">
                                        <span class="comment-date text-muted">{{rvo.dbday}}</span>
                                        <h5>{{rvo.name}}</h5>
                                        <p>{{rvo.msg}}</p>
                                        <a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.toggleUpdate(rvo.no,rvo.msg)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</a>
                                        <a class="active a-btn" v-if="store.sessionId===rvo.id" @click="store.commonsDelete(rvo.no)">삭제</a>
                                        <a class="a-btn" v-if="store.sessionId!==''" @click="store.toggleReply(rvo.no)">{{store.reReplyNo===rvo.no?'취소':'댓글'}}</a>
                                        
                                        <div class="comment-form" v-if="store.upReplyNo===rvo.no">
                                            <textarea v-model="store.updateMsg[rvo.no]" cols="50" rows="5"></textarea>
                                            <button type="button" class="btn-primary" @click="store.replyUpdate(rvo.no)">수정</button>
                                        </div>
                                        <div class="comment-form" v-if="store.reReplyNo===rvo.no">
                                            <textarea v-model="store.replyMsg[rvo.no]" cols="50" rows="5"></textarea>
                                            <button type="button" class="btn-primary" @click="store.replyReply(rvo.no)">대댓글</button>
                                        </div>
                                    </div>
                                </div>
                                <ol class="children" v-if="rvo.group_tab===1">
                                    <li class="single_comment_area">
                                        <div class="comment-wrapper d-flex">
                                            <div class="comment-author">
                                                <img :src="rvo.sex==='남자'?'/img/man.png':'/img/woman.png'">
                                            </div>
                                            <div class="comment-content">
                                                <span class="comment-date text-muted">{{rvo.dbday}}</span>
                                                <h5>{{rvo.name}}</h5>
                                                <p>{{rvo.msg}}</p>
                                                <a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.toggleUpdate(rvo.no,rvo.msg)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</a>
                                                <a class="active a-btn" v-if="store.sessionId===rvo.id" @click="store.commonsDelete(rvo.no)">삭제</a>
                                            </div>
                                        </div>
                                    </li>
                                </ol>
                            </li>
                        </ol>
                    </div>

                    <div class="leave-comment-area clearfix" v-if="store.sessionId!==''">
                        <div class="comment-form">
                            <textarea ref="msgRef" v-model="store.msg" cols="80" rows="5" placeholder="댓글을 입력하세요"></textarea>
                            <button type="button" class="btn-primary" @click="store.commonsInsert(msgRef)">등록</button>
                        </div>
                    </div>

                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                                <li class="page-item" v-if="store.startPage>1">
                                    <a class="page-link" @click="store.movePage(store.startPage-1)">이전</a>
                                </li>
                                <li v-for="i in store.range" :class="i===store.curpage?'page-item active':'page-item'">
                                    <a class="page-link" @click="store.movePage(i)">{{i}}</a>
                                </li>
                                <li class="page-item" v-if="store.endPage<store.totalpage">
                                    <a class="page-link" @click="store.movePage(store.endPage+1)">다음</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <jsp:include page="../commons/toast.jsp"></jsp:include>
                <script>
                    const {onMounted, ref, createApp} = Vue
                    const {createPinia} = Pinia
                    const commonApp = createApp({
                        setup(){
                            const store = useCommonsRepleStore();
                            const msgRef = ref(null)
                            onMounted(() => {
                                store.sessionId = SESSION_ID
                                store.commonsListData(CNO)
                                store.connect(SESSION_ID)
                            })
                            return { store, msgRef }
                        }
                    })
                    commonApp.use(createPinia())
                    commonApp.mount("#comment")
                </script>
            </div>
        </div>
    </section>
</body>
</html>