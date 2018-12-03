import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { StatisticsServiceService } from "../statistics-service.service";
import { ToastrService } from "ngx-toastr";
import { DropdownMethods } from "../DropdownMethods";

@Component({
    selector: "app-more-filters",
    templateUrl: "./more-filters.component.html",
    styleUrls: ["./more-filters.component.scss"]
})
export class MoreFiltersComponent extends DropdownMethods implements OnInit {
    opcionSeleccionado: string = "0";
    verSeleccion: string = "";
    Date2Capturado: string = "";
    Date1Capturado: string = "";
    opcionDateSleccionado: Date;
    opcionDateSleccionado2: Date;
    paramType: string;
    months = [
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    ];

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        public statisticsService: StatisticsServiceService,
        public toastr: ToastrService,
        private dialogRef: MatDialogRef<MoreFiltersComponent>
    ) {
        super(statisticsService, toastr);
        this.initializeDataFromMainFilters(data);
    }

    ngOnInit() {
        this.getYears();
        this.getMonths();
        this.getDays();
        this.getWeeks();
        this.getDaysOfWeek();
        this.getQuartersOfYear();
        this.getDaysOfYear();
        this.getHours();
        this.getMinutes();
        this.getSeconds();
    }

    closeMenu() {
        this.dialogRef.close({
            companies: this.includeCompaniesDataOnReturn(),
            campaigns: this.includeCampaignsDataOnReturn(),
            channels: this.includeChannelsDataOnReturn(),
            integrators: this.includeIntegratorsDataOnReturn(),
            years: this.includeYearsDataOnReturn(),
            months: this.includeMonthsDataOnReturn(),
            days: this.includeDaysDataOnReturn(),
            weeks: this.includeWeeksDataOnReturn(),
            daysOfWeek: this.includeDaysOfWeekDataOnReturn(),
            quartersOfYear: this.includeQuartersOfYearDataOnReturn(),
            daysOfYear: this.includeDaysOfYearDataOnReturn(),
            hours: this.includeHoursDataOnReturn(),
            minutes: this.includeMinutesDataOnReturn(),
            seconds: this.includeSecondsDataOnReturn()
        });
    }

    includeCompaniesDataOnReturn() {
        return {
            selectedCompanies: this.selectedCompanies,
            selectedCompaniesIds: this.selectedCompaniesIds,
            companiesDropdown: this.companiesDropdown
        };
    }

    includeCampaignsDataOnReturn() {
        return {
            selectedCampaigns: this.selectedCampaigns,
            selectedCampaignsIds: this.selectedCampaignsIds,
            campaignsDropdown: this.campaignsDropdown
        };
    }

    includeChannelsDataOnReturn() {
        return {
            selectedChannels: this.selectedChannels,
            selectedChannelsIds: this.selectedChannelsIds,
            channelsDropdown: this.channelsDropdown
        };
    }

    includeIntegratorsDataOnReturn() {
        return {
            selectedIntegrators: this.selectedIntegrators,
            selectedIntegratorsIds: this.selectedIntegratorsIds,
            integratorsDropdown: this.integratorsDropdown
        };
    }

    includeYearsDataOnReturn() {
        return {
            selectedYears: this.selectedYears,
            selectedYearsIds: this.selectedYearsIds
        };
    }

    includeMonthsDataOnReturn() {
        return {
            selectedMonths: this.selectedMonths,
            selectedMonthsIds: this.selectedMonthsIds
        };
    }

    includeDaysDataOnReturn() {
        return {
            selectedDays: this.selectedDays,
            selectedDaysIds: this.selectedDaysIds
        };
    }

    includeWeeksDataOnReturn() {
        return {
            selectedWeeks: this.selectedWeeks,
            selectedWeeksIds: this.selectedWeeksIds
        };
    }

    includeDaysOfWeekDataOnReturn() {
        return {
            selectedDaysOfWeek: this.selectedDaysOfWeek,
            selectedDaysOfWeekIds: this.selectedDaysOfWeekIds
        };
    }

    includeQuartersOfYearDataOnReturn() {
        return {
            selectedQuartersOfYear: this.selectedQuartersOfYear,
            selectedQuartersOfYearIds: this.selectedQuartersOfYearIds
        };
    }

    includeDaysOfYearDataOnReturn() {
        return {
            selectedDaysOfYear: this.selectedDaysOfYear,
            selectedDaysOfYearIds: this.selectedDaysOfYearIds
        };
    }

    includeHoursDataOnReturn() {
        return {
            selectedHours: this.selectedHours,
            selectedHoursIds: this.selectedHoursIds
        };
    }

    includeMinutesDataOnReturn() {
        return {
            selectedMinutes: this.selectedMinutes,
            selectedMinutesIds: this.selectedMinutesIds
        };
    }

    includeSecondsDataOnReturn() {
        return {
            selectedSeconds: this.selectedSeconds,
            selectedSecondsIds: this.selectedSecondsIds
        };
    }

    initializeDataFromMainFilters(data: any) {
        this.setupCompaniesData(data);
        this.setupCampaingsData(data);
        this.setupChannelsData(data);
        this.setupIntegratorsData(data);
        this.setupYearsData(data);
        this.setupMonthsData(data);
        this.setupDaysData(data);
        this.setupWeeksData(data);
        this.setupDaysOfWeekData(data);
        this.setupQuartersOfYearData(data);
        this.setupDaysOfYearData(data);
        this.setupHoursData(data);
        this.setupMinutesData(data);
        this.setupSecondsData(data);
    }

    setupCompaniesData(data: any) {
        this.companiesDropdown = data.companiesDropdown;
        this.companiesDropdownSettings = data.companiesDropdownSettings;
        this.selectedCompanies = data.selectedCompanies;
        this.selectedCompaniesIds = data.selectedCompaniesIds;
    }

    setupCampaingsData(data: any) {
        this.campaignsDropdown = data.campaignsDropdown;
        this.campaignsDropdownSettings = data.campaignsDropdownSettings;
        this.selectedCampaigns = data.selectedCampaigns;
        this.selectedCampaignsIds = data.selectedCampaignsIds;
    }

    setupChannelsData(data: any) {
        this.channelsDropdown = data.channelsDropdown;
        this.channelsDropdownSettings = data.channelsDropdownSettings;
        this.selectedChannels = data.selectedChannels;
        this.selectedChannelsIds = data.selectedChannelsIds;
    }

    setupIntegratorsData(data: any) {
        this.integratorsDropdown = data.integratorsDropdown;
        this.integratorsDropdownSettings = data.integratorsDropdownSettings;
        this.selectedIntegrators = data.selectedIntegrators;
        this.selectedIntegratorsIds = data.selectedIntegratorsIds;
    }

    setupYearsData(data: any) {
        this.selectedYears = data.selectedYears;
        this.selectedYearsIds = data.selectedYearsIds;
    }

    setupMonthsData(data: any) {
        this.selectedMonths = data.selectedMonths;
        this.selectedMonthsIds = data.selectedMonthsIds;
        console.log(data);
    }

    setupDaysData(data) {}

    setupWeeksData(data) {}

    setupDaysOfWeekData(data) {}

    setupQuartersOfYearData(data) {}

    setupDaysOfYearData(data) {}

    setupHoursData(data) {}

    setupMinutesData(data) {}

    setupSecondsData(data) {}

    getYears() {
        this.statisticsService.getYears().subscribe(data => {
            this.insertIntoDateDropdown(
                data,
                this.yearsDropdown,
                "year_id",
                "year_name"
            );
        });
    }

    getMonths() {
        this.statisticsService.getMonths().subscribe(data => {
            let anyData: any = data;
            let monthString = [];
            anyData.forEach(month => {
                monthString.push(this.months[month - 1]);
            });
            this.insertIntoDateDropdown(
                monthString,
                this.monthsDropdown,
                "month_id",
                "month_name"
            );
        });
    }

    getDays() {}

    getWeeks() {}

    getDaysOfWeek() {}

    getQuartersOfYear() {}

    getDaysOfYear() {}

    getHours() {}

    getMinutes() {}

    getSeconds() {}

    insertIntoDateDropdown(
        data: any,
        dateDropdown: any[],
        idField: string,
        nameField: string
    ) {
        var i = 1;
        data.forEach(date => {
            var item = {};
            item[idField] = i;
            item[nameField] = date;
            dateDropdown.push(item);
            i++;
        });
    }
}
