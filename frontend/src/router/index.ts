import { createRouter, createWebHistory, RouteRecordRaw, } from "vue-router";

const routes: Array<RouteRecordRaw> = [
	{
		path: "/",
		name: "Home",
		component: () => import("../components/pc/Home.vue"),
		meta: {
			title: "감귤"
		}
	}, {
		path: "/admin/dashboard",
		name: "Admin",
		component: () => import("../components/pc/admin/dashboard.vue"),
		meta: {
			title: "감귤: 관리"
		}
	},
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
});

export default router;
