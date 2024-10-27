import { Component, OnInit } from '@angular/core';
import { MealService } from '../../services/meal.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { MealResponse } from '../../types/meal-response.type';
import { MealTableComponent } from '../../components/meal-table/meal-table.component';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MealTableComponent
  ],
  providers: [DatePipe],
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  date: Date = new Date();
  currentDate = this.date.toISOString().split('T')[0];
  formattedDate = this.formatDate(this.date);
  isCalendarOpen: boolean = false;

  meals: MealResponse[] = [];

  constructor(
    private mealService: MealService,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.router.events.subscribe(event => {
      if(event.constructor.name === "NavigationEnd"){
        this.loadMeals(this.currentDate)
      }
    }) 
  }

  ngOnInit(): void {
    const date = new Date()
    this.loadMeals(date.toISOString().split('T')[0]);
  }

  async loadMeals(date: string) {
    try {
      this.meals = await this.mealService.getMeals("2024-10-10");
    } catch (error) {
      this.toastr.error('Error at fetching meals')
    }
  }

  formatDate(date: Date): string {
    return date.toLocaleDateString('en-US', {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'})
  }

  openCalendar(): void {
    this.isCalendarOpen = true;
  }

  closeCalendar(): void {
    this.isCalendarOpen = false;
  }

  onDateChange(event: any): void {
    this.loadMeals(this.currentDate);
  }
}
