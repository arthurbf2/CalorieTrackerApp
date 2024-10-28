import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../../services/food.service';
import { FoodResponse } from '../../types/food-response.type';
import { MealService } from '../../services/meal.service';

@Component({
  selector: 'app-food-search',
  templateUrl: './food-search.component.html',
  styleUrls: ['./food-search.component.scss'],
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
  ]
})
export class FoodSearchComponent {
  searchQuery: string = '';
  searchResults: FoodResponse[] = [];
  selectedFood: FoodResponse | null = null;
  quantity: number = 1;
  mealType: string = '';

  constructor(
    private router: Router,
    private foodService: FoodService,
    private mealService: MealService
  ) {}

  async searchFood() {
    try{
      this.searchResults = await this.foodService.getFoodList(this.searchQuery)
    } catch(error) {
      console.log(error)
    }
  }

  selectFood(food: any) {
    this.selectedFood = food;
  }

  async confirm() {
    if(this.selectedFood){
      await this.mealService.addMealItem(this.selectedFood.id, this.quantity, this.mealType)
      this.router.navigate(['user-dashboard'])
    }
  }
}
