const {createApp,onMounted,ref} = Vue
  const {createPinia} = Pinia
  const jejuApp=createApp({
	  setup(){
		  const store=useJejuStore()
		  const selectedRef=ref('12')
		  const findRef=ref('해수욕장')
		  onMounted(()=>{
			  store.jejuFindData()
		  })
		  
		  return {
			  
			 store ,
			 findRef,
			 selectedRef
		  }
	  }
  })
  jejuApp.use(createPinia())
  jejuApp.mount("#busan_find")