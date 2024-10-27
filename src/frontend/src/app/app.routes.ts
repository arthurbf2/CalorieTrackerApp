import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignUpComponent } from './pages/signup/signup.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { AuthGuard } from './services/auth-guard.service';
import { FoodSearchComponent } from './pages/food-search/food-search.component';

export const routes: Routes = [
    {
        path: "login",
        component: LoginComponent
    },
    {
        path: "sign-up",
        component: SignUpComponent
    },
    {
        path:"user-dashboard",
        component: UserDashboardComponent,
        canActivate: [AuthGuard]
    },
    {
        path:"food-search",
        component: FoodSearchComponent,
        canActivate: [AuthGuard]
    }
];
