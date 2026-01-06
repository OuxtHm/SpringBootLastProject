const {createApp, onMounted, ref} = Vue
const {createPinia} = Pinia

const busanApp = createApp({
	setup(){
		// store 읽기
		const store=useBusanStore()
		const addressRef=ref(null)
		
		// ref
		// onMounted()
		onMounted(()=>{
			store.busanFindData()
		})
		// return
		return {
			store,
			addressRef
		}
		
	}
})
busanApp.use(createPinia())
busanApp.mount('#busan_find')