import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DateService {
  private date: Date = new Date();

  getCurrentDate(): Date {
    return this.date;
  }

  setCurrentDate(date: Date): void {
    this.date = date;
  }

  getDateISOString(): string {
    return this.date.toISOString().split('T')[0];
  }

  getFormattedDate(): string {
    return this.date.toLocaleDateString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
  }
}
