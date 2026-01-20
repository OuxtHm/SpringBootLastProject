const {defineStore} = Pinia

const useAdminStore = defineStore('adminpage', {
	state:()=>({
		reserve_list:[]
	}),
	actions:{
		async dataRecv(){
			const res = await api.get('/admin/reserve_list_vue/')
			this.reserve_list = res.data
		}
	}
})