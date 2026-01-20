const {defineStore} = Pinia

const useMypageStore = defineStore('mypage', {
	state:()=>({
		reserve_list:[]
	}),
	actions:{
		async dataRecv(){
			const res = await api.get('/mypage/reserve_list_vue/')
			console.log(res.data)
			this.reserve_list = res.data
		}
	}
})