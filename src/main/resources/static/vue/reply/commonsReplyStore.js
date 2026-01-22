const { defineStore } = Pinia

const useCommonsRepleStore = defineStore('commons_reply', {
	// 공통 사용변수 => 변경이 될때마다 HTML에 반영 
	//                           ---- mount()
	state: () => ({
		list: [],
		curpage: 1,
		startPage: 0,
		endPage: 0,
		totalPage: 0,
		cno: 0,
		sessionId: '',
		msg: '',
		count: 0,
		//update관련 
		msg: '',
		upReplyNo: null,
		updateMsg: {},
		reReplyNo: null,
		replyMsg: {},
		stomp: null
	}),
	getters: {
		//  페이지 출력 
		range: (state) => {
			const arr = []
			for (let i = state.startPage; i <= state.endPage; i++) {
				arr.push(i) // push (저장) , pop (삭제)
			}

			return arr
		}
	},
	actions: {
		connect(id) {
			const sock = new SockJS('/ws')
			this.stomp = Stomp.over(sock)

			// 구독	=> 데이터를 받아오는 것 => 어디에 출력할 것인지
			// 어떤 URL인 경우인지 확인
			/*
				this.stomp.connect({}, ()=>{}, ()=>{})
								  ---1  ----2   -----3
						1: headers
						2: connectionCallback
						3: error
			*/
			this.stomp.connect({}, () => {
				this.stomp.subscribe('/sub/notice' + id, msg => {
					this.showToast(msg.body)
					this.dataRecv()
				})
			})
		},
		showToast(message) {
			document.getElementById("toastMsg").innerText = message
			const toastEl = document.getElementById("reserveToast")
			const toast = new bootstrap.Toast(toastEl, {
				delay: 5000
			})
			toast.show()
		},
		// then(responae=>{})
		setPageData(data) {
			this.list = data.list
			this.curpage = data.curpage
			this.startPage = data.startPage
			this.endPage = data.endPage
			this.cno = data.cno
			this.totalPage = data.totalPage
			this.count = data.count
		},
		// prev() / next() / pageChange()
		movePage(page) {
			this.curpage = page
			this.commonsListData()
		},
		async commonsListData(cno) {
			this.cno = cno
			const res = await api.get('/commons/list_vue/', {
				params: {
					page: this.curpage,
					cno: cno
				}
			})
			this.setPageData(res.data)
		},
		async commonsInsert(msgRef) {
			if (this.msg === '') {
				msgRef?.focus()
				return
			}
			const res = await api.post('/commons/insert_vue/', {
				cno: this.cno,
				msg: this.msg
			})
			this.setPageData(res.data)
		},
		// 삭제
		async commonsDelete(no) {
			const res = await api.delete('/commons/delete_vue/', {
				params: {
					no: no,
					cno: this.cno,
					page: this.curpage
				}

			})
			this.setPageData(res.data)
		},
		// update 
		toggleUpdate(no, msg) {
			this.upReplyNo = this.upReplyNo === no ? null : no
			this.updateMsg[no] = msg
			this.reReplyNo = null
		},
		async replyUpdate(no) {
			const res = await api.put('/commons/update_vue/', {
				no: no,
				cno: this.cno,
				page: this.curpage,
				msg: this.updateMsg[no]
			})
			this.setPageData(res.data)
			this.upReplyNo = null
		},
		// reply
		toggleReply(no, msg) {
			this.reReplyNo = this.reReplyNo === no ? null : no
			//this.replyMsg[no]=msg
			this.upReplyNo = null
		},
		async replyReply(no) {
			const res = await api.post('/commons/reply_reply_insert_vue/', {
				no: no,
				cno: this.cno,
				page: this.curpage,
				msg: this.updateMsg[no]
			})
			this.setPageData(res.data)
			this.reReplyNo = null
		}
	}
})
function hideToast() {
	const toast = document.getElementById("reserveToast");
	toast.classList.remove("show");
}