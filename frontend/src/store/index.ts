import { createStore } from "vuex";

export default createStore({
  state: {
    bannerData:[{id:123123,img:"temp",word:"temp",date:"temp",link:"temp"}]
  },
  mutations: {
    REMOVE_BANNER(state, index){
      state.bannerData.splice(index,1);
    },
    ADD_BANNER(state){
      state.bannerData.push({id:Math.random() * 1000000 / Math.random(),img:"temp",word:"temp",date:"temp",link:"temp"});
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
