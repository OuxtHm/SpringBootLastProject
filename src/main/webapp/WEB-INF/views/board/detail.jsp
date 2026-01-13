<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a-btn {
   cursor:pointer;
}
</style>
</head>
<script>
const SESSION_ID='${sessionScope.userid}'
const BNO='${param.no}'
</script>
<body>
    <div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>상세 보기</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/main"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item"><a href="/board/list"><i class="fa fa-home" aria-hidden="true"></i> 자유 게시판</a></li>
                            <li class="breadcrumb-item active" aria-current="page">상세 보기</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row" style="width:800px;margin: 0px auto;">
               <table class="table">
                  <tbody>
                     <tr>
                        <th width="20%" class="table-danger text-center">번호</th>
                        <td width="30%" class="text-center">${vo.no }</td>
                        <th width="20%" class="table-danger text-center">작성일</th>
                        <td width="30%" class="text-center">${vo.dbday }</td>
                     </tr>
                     <tr>
                        <th width="20%" class="table-danger text-center">이름</th>
                        <td width="30%" class="text-center">${vo.name }</td>
                        <th width="20%" class="table-danger text-center">조회수</th>
                        <td width="30%" class="text-center">${vo.hit }</td>
                     </tr>
                     <tr>
                        <th width="20%" class="table-danger text-center">제목</th>
                        <td colspan="3">${vo.subject }</td>
                     </tr>
                     <tr>
                        <td colspan="4">
                        <pre style="background-color: white;white-sapce:pre-wrap;border:none;" >${vo.content }</pre>
                        </td>
                     </tr>
                     <tr>
                        <td colspan="4" class="text-right">
                           <a href="/board/list" class="btn btn-sm btn-success">목록</a>
                           <a href="/board/update?no=${vo.no }" class="btn btn-sm btn-warning">수정</a>
                           <a href="/board/delete?no=${vo.no }" class="btn btn-sm btn-danger">삭제</a>

                        </td>
                     </tr>
                  </tbody>
               </table>
                          <!-- Comment Area Start -->
                          <div id="comment">
                            <div class="comment_area section_padding_50 clearfix">
                                <h4 class="mb-30">{{store.count}} 댓글</h4>

                                <ol>
                                    <!-- Single Comment Area -->
                                    <li class="single_comment_area" v-for="(rvo,index) in store.list" :key="index">
                                        <div class="comment-wrapper d-flex">
                                            <!-- Comment Meta -->
                                            <div class="comment-author">
                                                <img src="/img/man.png" v-if="rvo.sex==='남자'">
                                                <img src="/img/woman.png" v-if="rvo.sex==='여자'">
                                            </div>
                                            <!-- Comment Content -->
                                            <div class="comment-content">
                                                <span class="comment-date text-muted">{{rvo.dbday}}</span>
                                                <h5>{{rvo.name}}</h5>
                                                <p>{{rvo.msg}}</p>
                                                <a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.toggleUpdate(rvo.no,rvo.msg)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</a>
                                                <a  @click="store.replyDelete(rvo.no)" class="active a-btn" href="#" v-if="store.sessionId===rvo.id" @click="store.replyDelete(rvo.no)">삭제</a>
                                                <div class="comment-form" v-if="store.upReplyNo===rvo.no">
                                              
                                            <form action="#" method="post">
                                              <textarea name="message" cols="60" rows="3" placeholder="Message" style="float: left;display: inline-block;resize: none;padding-left:10px;" v-model="store.updateMsg[rvo.no]" ref="msg"></textarea>
                                              <button type="button" class="btn-primary" style="float: left;width: 80px;height: 75px;display: inline-block;" @click="store.replyUpdate(rvo.no)">댓글수정</button>              
                                            </form>  
                                            </div>
                                            </div>
                                        </div> 
                                    </li>
                                </ol>
                            </div>

                            <!-- Leave A Comment -->
                            <div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId!=''">
                                <div class="comment-form">
                                  <textarea name="message" cols="80" rows="5" placeholder="Message" style="float: left;display: inline-block;resize: none;padding-left:10px;" v-model="store.msg" ref="msg"></textarea>
                                  <button type="submit" class="btn-primary" style="float: left;width: 80px;height: 122px;display: inline-block;" @click="store.replyInsert(msgRef)">댓글쓰기</button>
                                </div>
                            </div>

                        </div>
                        <script src="/vue/axios.js"></script>
                        <script src="/vue/reply/boardReplyStore.js"></script>
                        <script>
                           const {createApp,onMounted, ref} = Vue
                           const {createPinia} = Pinia
                           const commentApp = createApp({
                        setup(){
                           const store=useBoardReplyStore()
                           const msgRef=ref(null)
                           onMounted(()=>{
                              store.sessionId = SESSION_ID
                              store.replyListData(BNO)
                           })
                           return {
                              store
                           }
                        }
                           
                           })
                           commentApp.use(createPinia())
                           commentApp.mount('#comment')
                        </script>
                    </div>
                   </div>
       </section>

</body>
</html>