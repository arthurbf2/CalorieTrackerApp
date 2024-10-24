import { Component, OnInit } from '@angular/core';
import { MealService } from '../../services/meal.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { MealResponse } from '../../types/meal-response.type';
import { MealTableComponent } from '../../meal-table/meal-table.component';

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
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const date = new Date()
    this.fetchMeals(date.toISOString().split('T')[0]);
  }

  async fetchMeals(date: string) {
    try {
      this.meals = await this.mealService.getMeals("2024-10-10");
      console.log("MEAL: ", this.meals)
    } catch (error) {
      console.error('Error at fetching meals', error);
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
    this.fetchMeals(this.currentDate);
  }


  addFood(mealType: string): void {
    /*
    this.mealService.addMealItem(this.userId, mealType).subscribe({
      next: () => {
        this.toastr.success('meal item successfully added.');
        this.fetchMeals();
      },
      error: () => {
        this.toastr.error('Error');
      }
    });
    */
  }
}
