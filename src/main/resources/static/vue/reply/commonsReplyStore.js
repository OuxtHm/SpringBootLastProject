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
		count: 0
		//update관련 
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
		}
		// update 
		// reply

	}
})