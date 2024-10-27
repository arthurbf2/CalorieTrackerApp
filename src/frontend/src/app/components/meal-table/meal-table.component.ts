import { Component, Input } from '@angular/core';
import { MealResponse } from '../../types/meal-response.type';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meal-table',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './meal-table.component.html',
  styleUrls: ['./meal-table.component.scss']
})
export class MealTableComponent {
  @Input() meals: MealResponse[] = [];
  @Input() mealType: string = '';

  constructor(private router: Router) {}

  navigateToSearch() {
    this.router.navigate(['/food-search'])
  }

  getFilteredMeals() {
    return this.meals.filter(meal => meal.mealType === this.mealType);
  }
}
