import { createStore } from "vuex";

export default createStore({
  state: {
    bannerData:[] as any
  },
  mutations: {
    REMOVE_BANNER(state, index){
      state.bannerData.splice(index,1);
    },
    ADD_BANNER(state){
      state.bannerData.push({id:Math.random() * 1000000 / Math.random(),img:"",word:"",date:"",link:""});
    },
    SET_BANNER_IMG(state, payload){
      state.bannerData[payload.index].img = payload.src;
    },
    SET_BANNER_WORD(state, payload){
      state.bannerData[payload.index].word = payload.src;
    }
  },
  actions: {},
  modules: {},
});
