import { Component, OnInit } from '@angular/core';
import { MealService } from '../../services/meal.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { MealResponse } from '../../types/meal-response.type';
import { MealTableComponent } from '../../components/meal-table/meal-table.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from '@angular/forms';
import { DateService } from '../../services/date.service';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MealTableComponent,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule
  ],
  providers: [DatePipe],
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  isCalendarOpen: boolean = false
  currentDate: Date;
  formattedDate = this.dateService.getFormattedDate()
  meals: MealResponse[] = []
  totalCalories: number = 0;
  totalCarbs: number = 0;
  totalFat: number = 0;
  totalProtein: number = 0;
  isInitialLoad: boolean = true;

  constructor(
    private mealService: MealService,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService
  ) {
    this.currentDate = this.dateService.getCurrentDate()
    this.router.events.subscribe(event => {
      if(event.constructor.name === "NavigationEnd" && !this.isInitialLoad){
        this.loadMeals(this.dateService.getDateISOString())
      }
    }) 
  }

  ngOnInit(): void {
    this.currentDate = this.dateService.getCurrentDate()
    this.formattedDate = this.dateService.getFormattedDate()
    this.loadMeals(this.dateService.getDateISOString())
    this.isInitialLoad = false
  }

  onDateChange(event: any): void {
    const selectedDate = event.value;
    this.dateService.setCurrentDate(selectedDate);
    this.currentDate = selectedDate;
    this.formattedDate = this.dateService.getFormattedDate()
    this.loadMeals(this.dateService.getDateISOString());
  }

  async loadMeals(date: string) {
    try {
      this.meals = await this.mealService.getMeals(date)
      this.calculateTotals()
    } catch (error) {
      this.toastr.error('Error at fetching meals')
    }
  }

  calculateTotals(): void {
    this.totalCalories = 0;
    this.totalCarbs = 0;
    this.totalFat = 0;
    this.totalProtein = 0;
    this.meals.forEach(meal => {
      meal.mealItems.forEach(item => {
        this.totalCalories += item.calories;
        this.totalCarbs += item.carbs;
        this.totalFat += item.fats;
        this.totalProtein += item.proteins;
      });
    });
  }
  

  previousDate(): void {
    this.updateDate(-1)
  }

  nextDate(): void {
    this.updateDate(1)
  }

  updateDate(days: number): void {
    const newDate = new Date(this.currentDate);
    newDate.setDate(this.currentDate.getDate() + days)
    this.dateService.setCurrentDate(newDate);
    this.currentDate = newDate;
    this.formattedDate = this.dateService.getFormattedDate();
    this.loadMeals(this.dateService.getDateISOString())
  }

  openCalendar(): void {
    this.isCalendarOpen = true
  }

  closeCalendar(): void {
    this.isCalendarOpen = false
  }
}
