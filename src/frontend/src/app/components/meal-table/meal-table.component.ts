import { Component, Input } from '@angular/core';
import { MealResponse } from '../../types/meal-response.type';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ItemResponse } from '../../types/item-response.type';
import { MealService } from '../../services/meal.service';
import { Output, EventEmitter } from '@angular/core';


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
  @Output() itemDeleted = new EventEmitter<void>();

  constructor(private router: Router, private mealService: MealService) {}

  navigateToSearch() {
    this.router.navigate(['/food-search'])
  }

  getFilteredMeals() {
    return this.meals.filter(meal => meal.mealType === this.mealType);
  }

  async deleteItem(meal: MealResponse, item: ItemResponse) {
    await this.mealService.deleteMealItem(meal.id, item.id)
    const index = meal.mealItems.indexOf(item)
    if(index > -1) {
      meal.mealItems.splice(index, 1)
      meal.totalCalories = meal.mealItems.reduce((sum, i) => sum + i.calories, 0);
      meal.totalCarbs = meal.mealItems.reduce((sum, i) => sum + i.carbs, 0);
      meal.totalFat = meal.mealItems.reduce((sum, i) => sum + i.fats, 0);
      meal.totalProtein = meal.mealItems.reduce((sum, i) => sum + i.proteins, 0);
      this.itemDeleted.emit();
    }
  }
}
