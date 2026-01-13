const { defineStore } = Pinia

const useBoardReplyStore = defineStore('board_reply', {
	// 공통 변수
	state: () => ({
		list: [],
		count: 0,
		bno: '${vo.no}',
		sessionId: '${sessionScope.userid}',
		msg: '',
		upReplyNo: null,
		updateMsg: {}
	}),
	actions: {
		toggleUpdate(no, msg) {
			this.upReplyNo = this.upReplyNo === no ? null : no
			this.updateMsg[no] = msg
		},
		async replyListData(bno) {
			this.bno = bno
			const { data } = await api.get('/reply/list_vue/', {
				params: {
					bno: this.bno
				}
			})
			this.list = data.list
			this.count = data.count
		},
		async replyInsert(msgRef) {
			if (!this.msg.trim()) {
				msgRef?.focus()
				return
			}
			const { data } = await api.post('/reply/insert_vue/', {
				bno: this.bno,
				msg: this.msg
			})
			this.list = data.list
			this.count = data.count
			this.msg = ''
		},
		async replyDelete(no) {
			const { data } = await api.delete('/reply/delete_vue/', {
				params: {
					no: no,
					bno: this.bno
				}
			})
			this.list = data.list
			this.count = data.count
			this.msg = ''
		},
		async replyUpdate(no) {
			const { data } = await api.put('/reply/update_vue/', {
				bno: this.bno,
				msg: this.updateMsg[no],
				no: no
			})
			this.list = data.list
			this.count = data.count
			this.upReplyNo = null
		}
	}
})