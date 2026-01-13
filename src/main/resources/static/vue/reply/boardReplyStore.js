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
	})
})